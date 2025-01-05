package io.hailing.book_manager.controller;


import io.hailing.book_manager.common.Result;
import io.hailing.book_manager.entity.pojo.BookQuery;
import io.hailing.book_manager.entity.dto.BookDTO;
import io.hailing.book_manager.entity.pojo.Book;
import io.hailing.book_manager.entity.dto.PageQueryDTO;
import io.hailing.book_manager.entity.vo.BookVO;
import io.hailing.book_manager.service.BookService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author testjava
 * @since 2023-10-19
 */
@RestController
@CrossOrigin
@RequestMapping("/book")
@Api(tags = "书籍相关接口")
@Slf4j
public class BookController {

    @Autowired
    private BookService bookService;

    /**
     * 分页查询图书
     * @param
     * @return
     */
    @GetMapping("/page/{page}/{pageSize}")
    @ApiOperation("分页查询图书")
    public Result<Page> page(@PathVariable long page,@PathVariable long pageSize){
        log.info("分页查询：{}",page,pageSize);
        Page<Book> bookPage = new Page<>(page,pageSize);
        Page<BookVO> bookVOPage = new Page<>();
        LambdaQueryWrapper<Book> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.orderByDesc(Book::getCreateTime);
        bookService.page(bookPage,queryWrapper);

        //对象拷贝
        BeanUtils.copyProperties(bookPage,bookVOPage);
        return Result.success(bookVOPage);
    }

    /**
     * 图书分页条件查询
     * @return
     */
    @PostMapping("/pageCondition/{page}/{pageSize}")
    @ApiOperation("条件分页查询")
    public Result<Page> page(PageQueryDTO PageQueryDTO,
                             @RequestBody(required = false) BookQuery bookQuery){
        log.info("分页查询：{}",PageQueryDTO.getPage(),PageQueryDTO.getPageSize());
        Page<Book> pageInfo = new Page<>(PageQueryDTO.getPage(), PageQueryDTO.getPageSize());
        Page<BookVO> bookVOPage = new Page<>();
        //构建条件
        LambdaQueryWrapper<Book> queryWrapper = new LambdaQueryWrapper<>();
        //判断是否为空
        queryWrapper.like(bookQuery.getBookName() != null,Book::getBookName,bookQuery.getBookName())
                    .like(bookQuery.getAuthorName() != null,Book::getAuthorName,bookQuery.getAuthorName())
                    .like(bookQuery.getCategoryName() != null,Book::getCategoryName,bookQuery.getCategoryName())
                    .like(bookQuery.getPublisherName() != null,Book::getPublisherName,bookQuery.getPublisherName())
                    .orderByDesc(Book::getUpdateTime);
        bookService.page(pageInfo,queryWrapper);

        BeanUtils.copyProperties(pageInfo,bookVOPage);

        return Result.success(bookVOPage);
    }

    /**
     * 新增图书
     * @param bookDTO
     * @return
     */
    @PostMapping
    @ApiOperation("新增图书")
    public Result<Book> addBook(@RequestBody BookDTO bookDTO){
        log.info("新增图书:{}",bookDTO);
        Book book = new Book();
        BeanUtils.copyProperties(bookDTO,book);
        bookService.save(book);
        return Result.success();
    }

    /**
     * 根据id删除图书
     * @param id
     * @return
     */
    @ApiOperation("根据id删除图书")
    @DeleteMapping("/{id}")
    public Result deleteById(@PathVariable Long id){
        log.info("删除图书，id为:{}",id);
        bookService.removeById(id);
        return Result.success();
    }

    /**
     * 根据id获取图书
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    @ApiOperation("根据id获取图书")
    public Result<Book> getById(@PathVariable Integer id){
        log.info("根据id获取图书：{}",id);
        Book book = bookService.getById(id);
        return Result.success(book);
    }

    /**
     * 修改图书
     * @param book
     * @return
     */
    @PutMapping
    @ApiOperation("修改图书")
    public Result editBook(@RequestBody Book book){
        log.info("修改图书:{}",book);
        bookService.updateById(book);
        return Result.success();
    }
}


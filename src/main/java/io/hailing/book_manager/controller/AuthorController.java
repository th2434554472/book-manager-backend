package io.hailing.book_manager.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.hailing.book_manager.common.Result;
import io.hailing.book_manager.entity.pojo.Author;
import io.hailing.book_manager.entity.vo.AuthorVO;
import io.hailing.book_manager.service.AuthorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2023-10-19
 */
@RestController
@RequestMapping("/author")
@CrossOrigin
@Api(tags = "作者相关接口")
@Slf4j
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    /**
     * 分页查询
     *
     * @param page
     * @param pageSize
     * @return
     */
    @GetMapping("/page/{page}/{pageSize}")
    @ApiOperation("分页查询")
    public Result<Page> page(@PathVariable Long page, @PathVariable Long pageSize) {
        log.info("分页查询：{}", page, pageSize);
        Page<Author> pageInfo = new Page<>(page, pageSize);
        Page<AuthorVO> authorVOPage = new Page<>();
        LambdaQueryWrapper<Author> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.orderByDesc(Author::getCreateTime);
        authorService.page(pageInfo, queryWrapper);
        BeanUtils.copyProperties(pageInfo, authorVOPage);
        return Result.success(authorVOPage);
    }

    /**
     * 新增作者
     *
     * @param authorVO
     * @return
     */
    @PostMapping
    @ApiOperation("新增作者")
    public Result save(@RequestBody AuthorVO authorVO) {
        log.info("新增作者:{}", authorVO);
        Author author = new Author();
        BeanUtils.copyProperties(authorVO, author);
        authorService.save(author);
        return Result.success();
    }

    /**
     * 删除作者
     *
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    @ApiOperation("删除作者")
    public Result delete(@PathVariable Long id) {
        log.info("删除作者,id为:{}", id);
        authorService.removeById(id);
        return Result.success();
    }

    /**
     * 根据id查询作者信息
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    @ApiOperation("根据id查询作者信息")
    public Result<Author> getById(@PathVariable Long id){
        log.info("根据id查询作者信息:{}",id);
        Author author = authorService.getById(id);
        return Result.success(author);
    }

    /**
     * 修改作者信息
     * @param author
     * @return
     */
    @PutMapping
    @ApiOperation("修改作者信息")
    public Result update(@RequestBody Author author){
        log.info("修改作者信息：{}",author);
        authorService.updateById(author);
        return Result.success();
    }

    /**
     * 获取作者列表
     * @return
     */
    @GetMapping("/list")
    @ApiOperation("获取作者列表")
    public Result<List<Author>> list(){
        log.info("获取作者列表:{}");
        List<Author> list = authorService.list();
        return Result.success(list);
    }
}


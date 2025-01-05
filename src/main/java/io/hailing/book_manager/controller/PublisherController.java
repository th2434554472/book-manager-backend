package io.hailing.book_manager.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.hailing.book_manager.common.Result;
import io.hailing.book_manager.entity.pojo.Publisher;
import io.hailing.book_manager.entity.dto.PageQueryDTO;
import io.hailing.book_manager.entity.dto.PublisherDTO;
import io.hailing.book_manager.service.PublisherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
@Slf4j
@Api(tags = "出版社相关接口")
@RequestMapping("/publisher")
public class PublisherController {

    @Autowired
    private PublisherService publisherService;

    /**
     * 分页查询
     * @param pageQueryDTO
     * @return
     */
    @GetMapping("/page/{page}/{pageSize}")
    @ApiOperation("分页查询")
    public Result<Page> page(PageQueryDTO pageQueryDTO){
        log.info("分页查询：{}",pageQueryDTO.getPage(),pageQueryDTO.getPageSize());
        Page<Publisher> pageInfo = new Page<>(pageQueryDTO.getPage(), pageQueryDTO.getPageSize());
        LambdaQueryWrapper<Publisher> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.orderByDesc(Publisher::getName);
        publisherService.page(pageInfo,queryWrapper);
        return Result.success(pageInfo);
    }

    /**
     * 添加出版社
     * @param publisherDTO
     * @return
     */
    @PostMapping
    @ApiOperation("添加出版社")
    public Result<Publisher> save(@RequestBody PublisherDTO publisherDTO){
        log.info("添加出版社：{}",publisherDTO);
        Publisher publisher = new Publisher();
        BeanUtils.copyProperties(publisherDTO,publisher);
        publisherService.save(publisher);
        return Result.success();
    }

    /**
     * 根据id删除出版社
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    @ApiOperation("根据id删除出版社")
    public Result deleteById(@PathVariable int id){
        log.info("根据id删除出版社:{}",id);
        publisherService.removeById(id);
        return Result.success();
    }

    /**
     * 根据id查询出版社信息
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    @ApiOperation("根据id查询出版社信息")
    public Result<Publisher> getById(@PathVariable Integer id){
        log.info("根据id查询出版社信息:{}",id);
        Publisher publisher = publisherService.getById(id);
        return Result.success(publisher);
    }

    /**
     * 修改出版社
     * @param publisher
     * @return
     */
    @PutMapping
    @ApiOperation("修改出版社")
    public Result update(@RequestBody Publisher publisher){
        log.info("修改出版社：{}",publisher);
        publisherService.updateById(publisher);
        return Result.success();
    }

    /**
     * 获取出版社列表
     * @return
     */
    @GetMapping("/list")
    @ApiOperation("获取出版社列表")
    public Result<List<Publisher>> list(){
        List<Publisher> list = publisherService.list();
        return Result.success(list);
    }
}


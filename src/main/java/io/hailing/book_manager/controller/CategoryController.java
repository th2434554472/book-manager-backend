package io.hailing.book_manager.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.hailing.book_manager.common.Result;
import io.hailing.book_manager.entity.Category;
import io.hailing.book_manager.entity.dto.CategoryDTO;
import io.hailing.book_manager.entity.dto.CategoryQueryDTO;
import io.hailing.book_manager.entity.dto.PageQueryDTO;
import io.hailing.book_manager.entity.vo.CategoryVO;
import io.hailing.book_manager.service.CategoryService;
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
 * @since 2023-10-23
 */
@RestController
@Slf4j
@RequestMapping("/category")
@CrossOrigin
@Api(tags = "分类相关接口")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    /**
     * 分类查询
     * @return
     */
    @PostMapping("/pageCondition/{page}/{pageSize}")
    @ApiOperation("分类查询")
    public Result<Page> page(PageQueryDTO pageQueryDTO, @RequestBody(required = false) CategoryQueryDTO categoryQueryDTO){
        log.info("分类查询：{}",pageQueryDTO.getPage(),pageQueryDTO.getPageSize(),categoryQueryDTO.getCategoryName());
        Page<Category> pageInfo = new Page<>(pageQueryDTO.getPage(), pageQueryDTO.getPageSize());
        Page<CategoryVO> categoryVOPage = new Page<>();
        LambdaQueryWrapper<Category> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(categoryQueryDTO.getCategoryName() != null,Category::getName,categoryQueryDTO.getCategoryName())
                .orderByDesc(Category::getCreateTime);
        categoryService.page(pageInfo,queryWrapper);

        BeanUtils.copyProperties(pageInfo,categoryVOPage);
        return Result.success(categoryVOPage);
    }

    /**
     * 新增分类
     * @param categoryDTO
     * @return
     */
    @PostMapping("/save")
    @ApiOperation("新增分类")
    public Result<Category> addCategory(@RequestBody CategoryDTO categoryDTO){
        log.info("新增分类：{}",categoryDTO);
        Category category = new Category();
        BeanUtils.copyProperties(categoryDTO,category);
        categoryService.save(category);
        return Result.success();
    }

    /**
     * 删除分类
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    @ApiOperation("删除分类")
    public Result deleteCategory(@PathVariable Integer id){
        log.info("删除分类,id为:{}",id);
        categoryService.removeById(id);
        return Result.success();
    }

    /**
     * 根据id查询分类
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    @ApiOperation("根据id查询分类")
    public Result<Category> getById(@PathVariable Integer id){
        log.info("根据id查询分类:{}",id);
        Category category = categoryService.getById(id);
        return Result.success(category);
    }

    /**
     * 修改分类
     * @param category
     * @return
     */
    @PutMapping
    @ApiOperation("修改分类")
    public Result updateCategory(@RequestBody Category category){
        log.info("修改分类:{}",category);
        categoryService.updateById(category);
        return Result.success();
    }

    /**
     * 获取分类
     * @return
     */
    @GetMapping("/list")
    @ApiOperation("获取分类")
    public Result<List<Category>> getList(){

        List<Category> list = categoryService.list();
        return Result.success(list);
    }
}


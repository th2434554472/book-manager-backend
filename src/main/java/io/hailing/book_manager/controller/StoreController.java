package io.hailing.book_manager.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.hailing.book_manager.common.Result;
import io.hailing.book_manager.entity.pojo.Store;
import io.hailing.book_manager.service.StoreService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/store")
@CrossOrigin
@Slf4j
@Api(tags = "店铺接口")
class StoreController {

    @Autowired
    private StoreService storeService;

    /**
     * 分页查询店铺
     * @param page
     * @param pageSize
     * @return
     */
    @GetMapping("/page/{page}/{pageSize}")
    @ApiModelProperty("分页查询店铺")
    public Result<Page> page(@PathVariable int page, @PathVariable int pageSize) {
        Page<Store> pageInfo = new Page<>(page, pageSize);
        LambdaQueryWrapper<Store> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.orderByDesc(Store::getCreateTime);
        storeService.page(pageInfo, queryWrapper);
        return Result.success(pageInfo);
    }

    /**
     * 保存店铺
     * @param store
     * @return
     */
    @PostMapping("/save")
    @ApiModelProperty("保存店铺")
    public Result saveStore(@RequestBody Store store) {
        storeService.save(store);
        return Result.success();
    }

    /**
     * 更新店铺
     * @param store
     * @return
     */
    @PutMapping("/update")
    @ApiModelProperty("更新店铺")
    public Result updateStore(Store store) {
        storeService.updateById(store);
        return Result.success();
    }

    /**
     * 根据id获取店铺
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    @ApiModelProperty("根据id获取店铺")
    public Result<Store> getStoreById(@PathVariable Long id) {
        return Result.success(storeService.getById(id));
    }

    /**
     * 删除店铺
     * @param id
     * @return
     */
    @DeleteMapping("/delete/{id}")
    @ApiModelProperty("删除店铺")
    public Result deleteStore(@PathVariable Long id) {
        storeService.removeById(id);
        return Result.success();
    }

    /**
     * 获取店铺列表
     * @return
     */
    @GetMapping("/list")
    @ApiModelProperty("获取店铺列表")
    public Result getStoreList(){
        List<Store> list = storeService.list();
        return Result.success(list);
    }
}
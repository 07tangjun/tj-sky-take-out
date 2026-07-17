package com.sky.controller.admin;

import com.sky.dto.CategoryDTO;
import com.sky.dto.CategoryPageQueryDTO;
import com.sky.entity.Category;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.CategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 菜品分类管理
 */
@RestController
@RequestMapping("/admin/category")
@Api(tags = "菜品分类管理")
@Slf4j
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    /**
     * 新增菜品分类
     * @return
     */
    @PostMapping()
    @ApiOperation("新增分类")
    public Result<String> save(@RequestBody CategoryDTO categoryDTO){
        log.info("新增菜品分类：{}", categoryDTO);
        categoryService.save(categoryDTO);
        return Result.success();
    }

    /**
     * 分页查询菜品分类
     * @param categoryPageQueryDTO
     * @return
     */
    @GetMapping("/page")
    @ApiOperation("分页查询分类")
    public Result<PageResult> pageQuery(CategoryPageQueryDTO categoryPageQueryDTO){
        log.info("分页查询菜品分类，参数为：{}", categoryPageQueryDTO);
        PageResult pageResult = categoryService.pageQuery(categoryPageQueryDTO);
        return Result.success(pageResult);
    }

    /**
     * 启用禁用分类
     * @return
     */
    @PostMapping("/status/{status}")
    @ApiOperation("启用禁用分类")
    public Result startOrStop(@PathVariable Integer status,Long id){
        log.info("启用禁用分类，状态：{}，id：{}", status, id);
        categoryService.startOrStop(status, id);
        return Result.success();
    }

    /**
     * 修改菜品分类
     * @param categoryDTO
     * @return
     */
    @PutMapping
    @ApiOperation("修改菜品分类")
    public Result<Category> update(@RequestBody CategoryDTO categoryDTO){
        log.info("修改菜品分类：{}", categoryDTO);
        categoryService.update(categoryDTO);
        return Result.success();
    }

    /**
     * 根据id删除菜品分类
     * @param id
     * @return
     */
    @DeleteMapping()
    @ApiOperation("根据id删除菜品分类")
    public Result<String> delete(Long id){
        log.info("删除菜品分类：{}", id);
        categoryService.delete(id);
        return Result.success();
    }

    @GetMapping("/list")
    @ApiOperation("根据类型查询菜品分类")
    public Result<List<Category>> list(@RequestParam Integer type){
        log.info("查询所有菜品分类，类型为：{}", type);
        List<Category> categoryList = categoryService.list(type);
        return Result.success(categoryList);
    }
}

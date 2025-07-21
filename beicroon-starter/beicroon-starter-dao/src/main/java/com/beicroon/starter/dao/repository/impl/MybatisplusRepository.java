package com.beicroon.starter.dao.repository.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
import com.beicroon.construct.base.entity.PageInfo;
import com.beicroon.construct.base.entity.QueryDTO;
import com.beicroon.construct.base.helper.PageInfoHelper;
import com.beicroon.construct.constant.SystemConstant;
import com.beicroon.construct.database.model.GenericModel;
import com.beicroon.construct.exception.utils.ExceptionUtils;
import com.beicroon.construct.utils.EmptyUtils;
import com.beicroon.construct.utils.ListUtils;
import com.beicroon.construct.utils.NumberUtils;
import com.beicroon.provider.mybatisplus.helper.ConvertorHelper;
import com.beicroon.provider.mybatisplus.mapper.GenericMapper;
import com.beicroon.starter.dao.repository.GenericRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Consumer;

public class MybatisplusRepository<M extends GenericMapper<T>, T extends GenericModel> implements GenericRepository<T> {

    @Autowired
    private M baseMapper;

    private M getBaseMapper() {
        return this.baseMapper;
    }

    @Override
    public T getById(Long id) {
        if (EmptyUtils.isNotId(id)) {
            return null;
        }

        QueryWrapper<T> wrapper = this.newQueryWrapper();

        wrapper.eq(SystemConstant.PRIMARY_KEY_NAME, id);

        return this.first(wrapper);
    }

    @Override
    public T getByIdOrError(Long id, String message) {
        if (EmptyUtils.isNotId(id)) {
            throw ExceptionUtils.business(message);
        }

        T model = this.getById(id);

        if (model == null) {
            throw ExceptionUtils.business(message);
        }

        return model;
    }

    @Override
    public T first(SFunction<T, ?> field, Object value) {
        LambdaQueryWrapper<T> wrapper = this.newLambdaQueryWrapper();

        wrapper.eq(field, value);

        return this.first(wrapper);
    }

    @Override
    public <R> R first(SFunction<T, ?> field, Object value, SFunction<T, R> select) {
        LambdaQueryWrapper<T> wrapper = this.newLambdaQueryWrapper();

        wrapper.select(select);
        wrapper.eq(field, value);

        T first = this.first(wrapper);

        if (first == null) {
            return null;
        }

        return ListUtils.first(ListUtils.toList(ListUtils.of(first), select));
    }

    @Override
    public T first(QueryWrapper<T> wrapper) {
        return this.first(wrapper.lambda());
    }

    @Override
    public T first(LambdaQueryWrapper<T> wrapper) {
        wrapper.last("LIMIT 1");

        return this.getBaseMapper().selectOne(wrapper);
    }

    @Override
    public T selectOne(QueryWrapper<T> wrapper) {
        return this.getBaseMapper().selectOne(wrapper);
    }

    @Override
    public T selectOne(LambdaQueryWrapper<T> wrapper) {
        return this.getBaseMapper().selectOne(wrapper);
    }

    @Override
    public T firstOrError(SFunction<T, ?> field, Object value, String message) {
        T model = this.first(field, value);

        if (model == null) {
            throw ExceptionUtils.business(message);
        }

        return model;
    }

    @Override
    public T firstOrError(QueryWrapper<T> wrapper, String message) {
        T model = this.first(wrapper);

        if (model == null) {
            throw ExceptionUtils.business(message);
        }

        return model;
    }

    @Override
    public T firstOrError(LambdaQueryWrapper<T> wrapper, String message) {
        T model = this.first(wrapper);

        if (model == null) {
            throw ExceptionUtils.business(message);
        }

        return model;
    }

    @Override
    public List<T> list(QueryWrapper<T> wrapper) {
        return this.list(wrapper.lambda());
    }

    @Override
    public List<T> list(LambdaQueryWrapper<T> wrapper) {
        return this.getBaseMapper().selectList(wrapper);
    }

    @Override
    public List<T> listByIdsAndEnable(Collection<Long> ids) {
        if (EmptyUtils.isEmpty(ids)) {
            return EmptyUtils.emptyList();
        }

        QueryWrapper<T> wrapper = this.newQueryWrapper();

        wrapper.in(SystemConstant.PRIMARY_KEY_NAME, ids);
        wrapper.isNull(SystemConstant.DISABLE_KEY_NAME);

        return this.list(wrapper);
    }

    @Override
    public List<T> listEnable() {
        QueryWrapper<T> wrapper = this.newQueryWrapper();

        wrapper.isNull(SystemConstant.DISABLE_KEY_NAME);

        return this.list(wrapper);
    }

    @Override
    public List<T> list(SFunction<T, ?> field, Object value) {
        LambdaQueryWrapper<T> wrapper = this.newLambdaQueryWrapper();

        wrapper.eq(field, value);

        return this.list(wrapper);
    }

    @Override
    public List<T> list(SFunction<T, ?> field, Collection<?> values) {
        if (EmptyUtils.isEmpty(values)) {
            return EmptyUtils.emptyList();
        }

        LambdaQueryWrapper<T> wrapper = this.newLambdaQueryWrapper();

        wrapper.in(field, values);

        return this.list(wrapper);
    }

    @Override
    public <R> Set<R> list(SFunction<T, ?> field, Object value, SFunction<T, R> select) {
        LambdaQueryWrapper<T> wrapper = this.newLambdaQueryWrapper();

        wrapper.eq(field, value);

        return this.list(wrapper, select);
    }

    @Override
    public <R> Set<R> list(SFunction<T, ?> field, Collection<?> values, SFunction<T, R> select) {
        if (EmptyUtils.isEmpty(values)) {
            return EmptyUtils.emptySet();
        }

        LambdaQueryWrapper<T> wrapper = this.newLambdaQueryWrapper();

        wrapper.in(field, values);

        return this.list(wrapper, select);
    }

    @Override
    public <R> Set<R> listByIdsAndEnable(Collection<Long> ids, SFunction<T, R> select) {
        if (EmptyUtils.isEmpty(ids)) {
            return EmptyUtils.emptySet();
        }

        QueryWrapper<T> wrapper = this.newQueryWrapper();

        wrapper.in(SystemConstant.PRIMARY_KEY_NAME, ids);
        wrapper.isNull(SystemConstant.DISABLE_KEY_NAME);

        return this.list(wrapper, select);
    }

    @Override
    public <R> Set<R> list(QueryWrapper<T> wrapper, SFunction<T, R> select) {
        return this.list(wrapper.lambda(), select);
    }

    @Override
    public <R> Set<R> list(LambdaQueryWrapper<T> wrapper, SFunction<T, R> select) {
        wrapper.select(select);

        return ListUtils.toSet(this.list(wrapper), select);
    }

    @Override
    public <R> Set<R> listEnable(SFunction<T, R> select) {
        QueryWrapper<T> wrapper = this.newQueryWrapper();

        wrapper.isNull(SystemConstant.DISABLE_KEY_NAME);

        return this.list(wrapper, select);
    }

    @Override
    public List<T> listByIds(Collection<Long> ids) {
        if (EmptyUtils.isEmpty(ids)) {
            return EmptyUtils.emptyList();
        }

        QueryWrapper<T> wrapper = this.newQueryWrapper();

        wrapper.in(SystemConstant.PRIMARY_KEY_NAME, ids);

        return this.list(wrapper);
    }

    @Override
    public PageInfo<T> page(QueryDTO.PageInfo pageInfo, QueryWrapper<T> wrapper) {
        return this.page(pageInfo, wrapper.lambda());
    }

    @Override
    public PageInfo<T> page(QueryDTO.PageInfo pageInfo, LambdaQueryWrapper<T> wrapper) {
        PageInfo<T> data = new PageInfo<>();

        Page<T> page = new Page<>(PageInfoHelper.getPageOrDefault(pageInfo), PageInfoHelper.getSizeOrDefault(pageInfo));

        Page<T> pageData = this.getBaseMapper().selectPage(page, wrapper);

        data.setPage(pageData.getCurrent());
        data.setSize(pageData.getSize());
        data.setTotal(pageData.getTotal());
        data.setData(pageData.getRecords());

        return data;
    }

    @Override
    public Long count(SFunction<T, ?> field, Object value) {
        LambdaQueryWrapper<T> wrapper = this.newLambdaQueryWrapper();

        wrapper.eq(field, value);

        return this.count(wrapper);
    }

    @Override
    public Long count(SFunction<T, ?> field, Collection<?> values) {
        if (EmptyUtils.isEmpty(values)) {
            return EmptyUtils.emptyLong();
        }

        LambdaQueryWrapper<T> wrapper = this.newLambdaQueryWrapper();

        wrapper.in(field, values);

        return this.count(wrapper);
    }

    @Override
    public Long count(QueryWrapper<T> wrapper) {
        return this.count(wrapper.lambda());
    }

    @Override
    public Long count(LambdaQueryWrapper<T> wrapper) {
        return this.getBaseMapper().selectCount(wrapper);
    }

    @Override
    public void chunkById(QueryWrapper<T> wrapper, int size, Consumer<Collection<T>> consumer) {
        wrapper.orderByAsc(SystemConstant.PRIMARY_KEY_NAME);

        AtomicLong lastId = new AtomicLong(0);

        while (true) {
            QueryWrapper<T> mybatisWrapper = wrapper.clone();

            mybatisWrapper.gt(SystemConstant.PRIMARY_KEY_NAME, lastId.get());
            mybatisWrapper.last(String.format("LIMIT %d", size));

            List<T> data = this.getBaseMapper().selectList(mybatisWrapper);

            if (EmptyUtils.isEmpty(data)) {
                break;
            }

            lastId.set(data.get(data.size() - 1).getId());

            consumer.accept(data);

            if (NumberUtils.lt(data.size(), size)) {
                break;
            }
        }
    }

    @Override
    public boolean insert(T model) {
        return SqlHelper.retBool(this.getBaseMapper().insert(model));
    }

    @Override
    public boolean insert(Collection<T> models) {
        return SqlHelper.retBool(this.getBaseMapper().insert(models));
    }

    @Override
    public boolean update(UpdateWrapper<T> wrapper) {
        ConvertorHelper.fillModifier(wrapper);

        return SqlHelper.retBool(this.getBaseMapper().update(wrapper));
    }

    @Override
    public boolean updateById(T model) {
        if (EmptyUtils.isNotId(model.getId())) {
            throw ExceptionUtils.business("主键不能为空");
        }

        ConvertorHelper.fillModifier(model);

        return SqlHelper.retBool(this.getBaseMapper().updateById(model));
    }

    @Override
    public boolean updateByIds(Collection<T> models) {
        for (T model : models) {
            this.updateById(model);
        }

        return true;
    }

    @Override
    public boolean existed(QueryWrapper<T> wrapper) {
        return this.existed(wrapper.lambda());
    }

    @Override
    public boolean existed(LambdaQueryWrapper<T> wrapper) {
        return this.first(wrapper) != null;
    }

    @Override
    public boolean existed(SFunction<T, ?> field, Object value) {
        LambdaQueryWrapper<T> wrapper = this.newLambdaQueryWrapper();

        wrapper.eq(field, value);

        return this.first(wrapper) != null;
    }

    @Override
    public boolean existed(SFunction<T, ?> field, Object value, Long exceptId) {
        QueryWrapper<T> wrapper = this.newQueryWrapper();

        wrapper.ne(SystemConstant.PRIMARY_KEY_NAME, exceptId);

        wrapper.lambda().eq(field, value);

        return this.first(wrapper) != null;
    }

    @Override
    public boolean existed(SFunction<T, ?> field, Collection<?> values, Long exceptId) {
        QueryWrapper<T> wrapper = this.newQueryWrapper();

        wrapper.ne(SystemConstant.PRIMARY_KEY_NAME, exceptId);
        wrapper.lambda().in(field, values);

        return this.getBaseMapper().exists(wrapper);
    }

    @Override
    public boolean remove(UpdateWrapper<T> wrapper) {
        ConvertorHelper.fillRemover(wrapper);

        return SqlHelper.retBool(this.getBaseMapper().update(wrapper));
    }

    @Override
    public boolean remove(SFunction<T, ?> field, Object value) {
        UpdateWrapper<T> wrapper = this.newUpdateWrapper();

        ConvertorHelper.fillRemover(wrapper);

        wrapper.lambda().eq(field, value);

        return SqlHelper.retBool(this.getBaseMapper().update(wrapper));
    }

    @Override
    public boolean remove(SFunction<T, ?> field, Collection<?> values) {
        UpdateWrapper<T> wrapper = this.newUpdateWrapper();

        ConvertorHelper.fillRemover(wrapper);

        wrapper.lambda().in(field, values);

        return SqlHelper.retBool(this.getBaseMapper().update(wrapper));
    }

    @Override
    public boolean removeById(Long id) {
        UpdateWrapper<T> wrapper = this.newUpdateWrapper();

        ConvertorHelper.fillRemover(wrapper);

        wrapper.eq(SystemConstant.PRIMARY_KEY_NAME, id);

        return SqlHelper.retBool(this.getBaseMapper().update(wrapper));
    }

    @Override
    public boolean removeByIds(Collection<Long> ids) {
        if (EmptyUtils.isEmpty(ids)) {
            return false;
        }

        UpdateWrapper<T> wrapper = this.newUpdateWrapper();

        ConvertorHelper.fillRemover(wrapper);

        wrapper.in(SystemConstant.PRIMARY_KEY_NAME, ids);

        return SqlHelper.retBool(this.getBaseMapper().update(wrapper));
    }

    @Override
    public boolean delete(QueryWrapper<T> wrapper) {
        return this.delete(wrapper.lambda());
    }

    @Override
    public boolean delete(LambdaQueryWrapper<T> wrapper) {
        return SqlHelper.retBool(this.getBaseMapper().delete(wrapper));
    }

    @Override
    public boolean delete(SFunction<T, ?> field, Object value) {
        LambdaQueryWrapper<T> wrapper = this.newLambdaQueryWrapper();

        wrapper.eq(field, value);

        return SqlHelper.retBool(this.getBaseMapper().delete(wrapper));
    }

    @Override
    public boolean delete(SFunction<T, ?> field, Collection<?> values) {
        LambdaQueryWrapper<T> wrapper = this.newLambdaQueryWrapper();

        wrapper.in(field, values);

        return SqlHelper.retBool(this.getBaseMapper().delete(wrapper));
    }

    @Override
    public boolean deleteById(Long id) {
        if (EmptyUtils.isNotId(id)) {
            return false;
        }

        QueryWrapper<T> wrapper = this.newQueryWrapper();

        wrapper.eq(SystemConstant.PRIMARY_KEY_NAME, id);

        return SqlHelper.retBool(this.getBaseMapper().delete(wrapper));
    }

    @Override
    public boolean deleteByIds(Collection<Long> ids) {
        if (EmptyUtils.isEmpty(ids)) {
            return false;
        }

        QueryWrapper<T> wrapper = this.newQueryWrapper();

        wrapper.in(SystemConstant.PRIMARY_KEY_NAME, ids);

        return SqlHelper.retBool(this.getBaseMapper().delete(wrapper));
    }

}

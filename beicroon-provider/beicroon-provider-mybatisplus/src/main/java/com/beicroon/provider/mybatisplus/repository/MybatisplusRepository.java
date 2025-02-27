package com.beicroon.provider.mybatisplus.repository;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
import com.beicroon.construct.annotation.TableField;
import com.beicroon.construct.base.entity.PageInfo;
import com.beicroon.construct.base.entity.QueryDTO;
import com.beicroon.construct.base.helper.PageInfoHelper;
import com.beicroon.construct.constant.SystemConstant;
import com.beicroon.construct.database.helper.WrapperHelper;
import com.beicroon.construct.database.lambda.SFunction;
import com.beicroon.construct.database.model.GenericModel;
import com.beicroon.construct.database.repository.GenericRepository;
import com.beicroon.construct.database.wrapper.IQueryWrapper;
import com.beicroon.construct.database.wrapper.IUpdateWrapper;
import com.beicroon.construct.exception.utils.ExceptionUtils;
import com.beicroon.construct.utils.ClassUtils;
import com.beicroon.construct.utils.EmptyUtils;
import com.beicroon.construct.utils.ListUtils;
import com.beicroon.construct.utils.NumberUtils;
import com.beicroon.provider.mybatisplus.helper.MybatisPlusHelper;
import com.beicroon.provider.mybatisplus.mapper.GenericMapper;
import com.beicroon.provider.mybatisplus.wrapper.MybatisPlusQueryWrapper;
import com.beicroon.provider.mybatisplus.wrapper.MybatisPlusUpdateWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.GenericTypeResolver;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Consumer;

public class MybatisplusRepository<M extends GenericMapper<T>, T extends GenericModel> implements GenericRepository<T> {

    @Autowired
    private M baseMapper;

    private Class<T> entityClass;

    private M getBaseMapper() {
        return this.baseMapper;
    }

    @SuppressWarnings("unchecked")
    private Class<T> resolveEntityClass() {
        return (Class<T>) GenericTypeResolver.resolveTypeArgument(this.baseMapper.getClass(), GenericMapper.class);
    }

    @Override
    public Class<T> getEntityClass() {
        if (this.entityClass == null) {
            this.entityClass = this.resolveEntityClass();
        }

        return this.entityClass;
    }

    @Override
    public IQueryWrapper<T> newQueryWrapper() {
        return new MybatisPlusQueryWrapper<>(this.getEntityClass());
    }

    @Override
    public IUpdateWrapper<T> newUpdateWrapper() {
        return new MybatisPlusUpdateWrapper<>(this.getEntityClass());
    }

    @Override
    public T getById(Long id) {
        if (EmptyUtils.isNotId(id)) {
            return null;
        }

        IQueryWrapper<T> wrapper = this.newQueryWrapper();

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
        IQueryWrapper<T> wrapper = this.newQueryWrapper();

        wrapper.eq(field, value);

        return this.first(wrapper);
    }

    @Override
    public T first(IQueryWrapper<T> wrapper) {
        QueryWrapper<T> mybatisWrapper = MybatisPlusHelper.getWrapper(wrapper);

        mybatisWrapper.last("LIMIT 1");

        return this.getBaseMapper().selectOne(mybatisWrapper);
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
    public List<T> list(IQueryWrapper<T> wrapper) {
        return this.getBaseMapper().selectList(MybatisPlusHelper.getWrapper(wrapper));
    }

    @Override
    public List<T> listByIdsAndEnable(Collection<Long> ids) {
        if (EmptyUtils.isEmpty(ids)) {
            return EmptyUtils.emptyList();
        }

        IQueryWrapper<T> wrapper = this.newQueryWrapper();

        wrapper.in(SystemConstant.PRIMARY_KEY_NAME, ids);
        wrapper.isNull(SystemConstant.DISABLE_KEY_NAME);

        return this.list(wrapper);
    }

    @Override
    public List<T> listEnable() {
        IQueryWrapper<T> wrapper = this.newQueryWrapper();

        wrapper.isNull(SystemConstant.DISABLE_KEY_NAME);

        return this.list(wrapper);
    }

    @Override
    public List<T> list(SFunction<T, ?> field, Object value) {
        IQueryWrapper<T> wrapper = this.newQueryWrapper();

        wrapper.eq(field, value);

        return this.list(wrapper);
    }

    @Override
    public List<T> list(SFunction<T, ?> field, Collection<?> values) {
        if (EmptyUtils.isEmpty(values)) {
            return EmptyUtils.emptyList();
        }

        IQueryWrapper<T> wrapper = this.newQueryWrapper();

        wrapper.in(field, values);

        return this.list(wrapper);
    }

    @Override
    public <R> Set<R> list(SFunction<T, ?> field, Object value, SFunction<T, R> select) {
        IQueryWrapper<T> wrapper = this.newQueryWrapper();

        wrapper.eq(field, value);

        return this.list(wrapper, select);
    }

    @Override
    public <R> Set<R> list(SFunction<T, ?> field, Collection<?> values, SFunction<T, R> select) {
        if (EmptyUtils.isEmpty(values)) {
            return EmptyUtils.emptySet();
        }

        IQueryWrapper<T> wrapper = this.newQueryWrapper();

        wrapper.in(field, values);

        return this.list(wrapper, select);
    }

    @Override
    public <R> Set<R> listByIdsAndEnable(Collection<Long> ids, SFunction<T, R> select) {
        if (EmptyUtils.isEmpty(ids)) {
            return EmptyUtils.emptySet();
        }

        IQueryWrapper<T> wrapper = this.newQueryWrapper();

        wrapper.in(SystemConstant.PRIMARY_KEY_NAME, ids);
        wrapper.isNull(SystemConstant.DISABLE_KEY_NAME);

        return this.list(wrapper, select);
    }

    @Override
    public <R> Set<R> list(IQueryWrapper<T> wrapper, SFunction<T, R> select) {
        wrapper.select(select);

        return ListUtils.toSet(this.list(wrapper), select);
    }

    @Override
    public <R> Set<R> listEnable(SFunction<T, R> select) {
        IQueryWrapper<T> wrapper = this.newQueryWrapper();

        wrapper.isNull(SystemConstant.DISABLE_KEY_NAME);

        return this.list(wrapper, select);
    }

    @Override
    public List<T> listByIds(Collection<Long> ids) {
        if (EmptyUtils.isEmpty(ids)) {
            return EmptyUtils.emptyList();
        }

        IQueryWrapper<T> wrapper = this.newQueryWrapper();

        wrapper.in(SystemConstant.PRIMARY_KEY_NAME, ids);

        return this.list(wrapper);
    }

    @Override
    public PageInfo<T> page(QueryDTO.PageInfo pageInfo, IQueryWrapper<T> wrapper) {
        PageInfo<T> data = new PageInfo<>();

        Page<T> page = new Page<>(PageInfoHelper.getPageOrDefault(pageInfo), PageInfoHelper.getSizeOrDefault(pageInfo));

        Page<T> pageData = this.getBaseMapper().selectPage(page, MybatisPlusHelper.getWrapper(wrapper));

        data.setPage(pageData.getCurrent());
        data.setSize(pageData.getSize());
        data.setTotal(pageData.getTotal());
        data.setData(pageData.getRecords());

        return data;
    }

    @Override
    public Long count(IQueryWrapper<T> wrapper) {
        return this.getBaseMapper().selectCount(MybatisPlusHelper.getWrapper(wrapper));
    }

    @Override
    public void chunkById(IQueryWrapper<T> wrapper, int size, Consumer<List<T>> consumer) {
        wrapper.orderByAsc(SystemConstant.PRIMARY_KEY_NAME);

        AtomicLong lastId = new AtomicLong(0);

        while (true) {
            QueryWrapper<T> mybatisWrapper = MybatisPlusHelper.getWrapper(wrapper).clone();

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
    public boolean update(IUpdateWrapper<T> wrapper) {
        return SqlHelper.retBool(this.getBaseMapper().update(MybatisPlusHelper.getWrapper(wrapper)));
    }

    @Override
    public boolean updateById(T model) {
        if (EmptyUtils.isNotId(model.getId())) {
            throw ExceptionUtils.business("主键不能为空");
        }

        IUpdateWrapper<T> wrapper = this.newUpdateWrapper();

        wrapper.eq(SystemConstant.PRIMARY_KEY_NAME, model.getId());

        ClassUtils.forAllFieldAndValue(model, (Field field, Object value) -> {
            if (value == null) {
                return;
            }

            TableField tableField = field.getAnnotation(TableField.class);

            if (tableField == null) {
                return;
            }

            wrapper.set(tableField.name(), value);
        });

        return SqlHelper.retBool(this.getBaseMapper().update(MybatisPlusHelper.getWrapper(wrapper)));
    }

    @Override
    public boolean existed(SFunction<T, ?> field, Object value) {
        IQueryWrapper<T> wrapper = this.newQueryWrapper();

        wrapper.eq(field, value);

        return this.first(wrapper) != null;
    }

    @Override
    public boolean existed(SFunction<T, ?> field, Object value, Long exceptId) {
        IQueryWrapper<T> wrapper = this.newQueryWrapper();

        wrapper.eq(field, value);
        wrapper.neq(SystemConstant.PRIMARY_KEY_NAME, exceptId);

        return this.first(wrapper) != null;
    }

    @Override
    public boolean existed(SFunction<T, ?> field, Collection<?> values, Long exceptId) {
        IQueryWrapper<T> wrapper = this.newQueryWrapper();

        wrapper.in(field, values);
        wrapper.neq(SystemConstant.PRIMARY_KEY_NAME, exceptId);

        return this.getBaseMapper().exists(MybatisPlusHelper.getWrapper(wrapper));
    }

    @Override
    public boolean remove(SFunction<T, ?> field, Object value) {
        IUpdateWrapper<T> wrapper = this.newUpdateWrapper();

        WrapperHelper.fillWrapperRemover(wrapper);

        wrapper.eq(field, value);

        return SqlHelper.retBool(this.getBaseMapper().update(MybatisPlusHelper.getWrapper(wrapper)));
    }

    @Override
    public boolean remove(SFunction<T, ?> field, Collection<?> values) {
        IUpdateWrapper<T> wrapper = this.newUpdateWrapper();

        WrapperHelper.fillWrapperRemover(wrapper);

        wrapper.in(field, values);

        return SqlHelper.retBool(this.getBaseMapper().update(MybatisPlusHelper.getWrapper(wrapper)));
    }

    @Override
    public boolean removeById(Long id) {
        IUpdateWrapper<T> wrapper = this.newUpdateWrapper();

        WrapperHelper.fillWrapperRemover(wrapper);

        wrapper.eq(SystemConstant.PRIMARY_KEY_NAME, id);

        return SqlHelper.retBool(this.getBaseMapper().update(MybatisPlusHelper.getWrapper(wrapper)));
    }

    @Override
    public boolean removeByIds(Collection<Long> ids) {
        if (EmptyUtils.isEmpty(ids)) {
            return false;
        }

        IUpdateWrapper<T> wrapper = this.newUpdateWrapper();

        WrapperHelper.fillWrapperRemover(wrapper);

        wrapper.in(SystemConstant.PRIMARY_KEY_NAME, ids);

        return SqlHelper.retBool(this.getBaseMapper().update(MybatisPlusHelper.getWrapper(wrapper)));
    }

    @Override
    public boolean delete(SFunction<T, ?> field, Object value) {
        IUpdateWrapper<T> wrapper = this.newUpdateWrapper();

        WrapperHelper.fillWrapperRemover(wrapper);

        wrapper.eq(field, value);

        return SqlHelper.retBool(this.getBaseMapper().delete(MybatisPlusHelper.getWrapper(wrapper)));
    }

    @Override
    public boolean delete(SFunction<T, ?> field, Collection<?> values) {
        IUpdateWrapper<T> wrapper = this.newUpdateWrapper();

        WrapperHelper.fillWrapperRemover(wrapper);

        wrapper.in(field, values);

        return SqlHelper.retBool(this.getBaseMapper().delete(MybatisPlusHelper.getWrapper(wrapper)));
    }

    @Override
    public boolean deleteById(Long id) {
        if (EmptyUtils.isNotId(id)) {
            return false;
        }

        IUpdateWrapper<T> wrapper = this.newUpdateWrapper();

        WrapperHelper.fillWrapperRemover(wrapper);

        wrapper.eq(SystemConstant.PRIMARY_KEY_NAME, id);

        return SqlHelper.retBool(this.getBaseMapper().delete(MybatisPlusHelper.getWrapper(wrapper)));
    }

    @Override
    public boolean deleteByIds(Collection<Long> ids) {
        if (EmptyUtils.isEmpty(ids)) {
            return false;
        }

        IUpdateWrapper<T> wrapper = this.newUpdateWrapper();

        WrapperHelper.fillWrapperRemover(wrapper);

        wrapper.in(SystemConstant.PRIMARY_KEY_NAME, ids);

        return SqlHelper.retBool(this.getBaseMapper().delete(MybatisPlusHelper.getWrapper(wrapper)));
    }

}

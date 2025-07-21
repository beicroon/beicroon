package com.beicroon.construct.utils;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.*;
import java.util.stream.Collectors;

public final class ListUtils {

    private ListUtils() {

    }

    @SafeVarargs
    public static <T> List<T> of(T ...t) {
        return Arrays.asList(t);
    }

    public static <T> T first(List<T> list) {
        if (EmptyUtils.isEmpty(list)) {
            return null;
        }

        return list.get(0);
    }

    public static <T> T first(List<T> list, Predicate<T> predicate) {
        if (EmptyUtils.isEmpty(list)) {
            return null;
        }

        for (T t : list) {
            if (predicate.test(t)) {
                return t;
            }
        }

        return null;
    }

    public static <T> T last(List<T> list) {
        if (EmptyUtils.isEmpty(list)) {
            return null;
        }

        return list.get(list.size() - 1);
    }

    public static <T, C extends Comparable<? super C>> List<T> sort(Collection<T> collection, Function<? super T, ? extends C> comparator) {
        return ListUtils.sort(collection, Comparator.comparing(comparator));
    }

    public static <T> List<T> sort(Collection<T> collection, Comparator<? super T> comparator) {
        if (EmptyUtils.isEmpty(collection)) {
            return EmptyUtils.emptyList();
        }

        return collection.stream().sorted(comparator).collect(Collectors.toList());
    }

    public static <T> void foreach(Collection<T> collection, Consumer<? super T> consumer) {
        if (EmptyUtils.isEmpty(collection)) {
            return;
        }

        for (T t : collection) {
            consumer.accept(t);
        }
    }

    public static <K, V> void foreach(Map<K, V> map, Consumer<? super V> consumer) {
        if (EmptyUtils.isEmpty(map)) {
            return;
        }

        for (V value : map.values()) {
            consumer.accept(value);
        }
    }

    public static <K, V> void foreach(Map<K, V> map, BiConsumer<? super K, ? super V> consumer) {
        if (EmptyUtils.isEmpty(map)) {
            return;
        }

        for (Map.Entry<K, V> entry : map.entrySet()) {
            consumer.accept(entry.getKey(), entry.getValue());
        }
    }

    public static <T> void foreach(T[] collection, Consumer<? super T> consumer) {
        if (EmptyUtils.isEmpty(collection)) {
            return;
        }

        for (T t : collection) {
            consumer.accept(t);
        }
    }

    public static <T> void foreach(T[] collection, BiConsumer<? super T, Integer> consumer) {
        if (EmptyUtils.isEmpty(collection)) {
            return;
        }

        AtomicInteger i = new AtomicInteger(0);

        for (T t : collection) {
            consumer.accept(t, i.getAndIncrement());
        }
    }

    public static <T> void foreach(Collection<T> collection, BiConsumer<? super T, Integer> consumer) {
        if (EmptyUtils.isEmpty(collection)) {
            return;
        }

        AtomicInteger i = new AtomicInteger(0);

        for (T t : collection) {
            consumer.accept(t, i.getAndIncrement());
        }
    }

    public static <T> List<T> filter(Collection<T> collection, Predicate<? super T> predicate) {
        if (EmptyUtils.isEmpty(collection)) {
            return EmptyUtils.emptyList();
        }

        List<T> list = new ArrayList<>(collection.size());

        for (T t : collection) {
            if (predicate.test(t)) {
                list.add(t);
            }
        }

        return list;
    }

    public static <T, R> Set<R> toSet(Collection<T> collection, Function<? super T, ? extends R> convertor) {
        if (EmptyUtils.isEmpty(collection)) {
            return EmptyUtils.emptySet();
        }

        Set<R> set = new HashSet<>(collection.size());

        for (T t : collection) {
            R r = convertor.apply(t);

            if (r == null) {
                continue;
            }

            set.add(r);
        }

        return set;
    }

    public static <T, R> List<R> toList(Collection<T> collection, Function<? super T, ? extends R> convertor) {
        if (EmptyUtils.isEmpty(collection)) {
            return EmptyUtils.emptyList();
        }

        List<R> list = new ArrayList<>(collection.size());

        for (T t : collection) {
            R r = convertor.apply(t);

            if (r == null) {
                continue;
            }

            list.add(r);
        }

        return list;
    }

    public static <T, R> List<R> toList(Collection<T> collection, BiFunction<? super T, Integer, ? extends R> convertor) {
        if (EmptyUtils.isEmpty(collection)) {
            return EmptyUtils.emptyList();
        }

        List<R> list = new ArrayList<>(collection.size());

        AtomicInteger i = new AtomicInteger(0);

        for (T t : collection) {
            R r = convertor.apply(t, i.getAndIncrement());

            if (r == null) {
                continue;
            }

            list.add(r);
        }

        return list;
    }

    public static <K, V, R> List<R> toList(Map<K, V> map, Function<V, R> mapper) {
        if (EmptyUtils.isEmpty(map)) {
            return EmptyUtils.emptyList();
        }

        List<R> list = new ArrayList<>(map.size());

        for (Map.Entry<K, V> entry : map.entrySet()) {
            R r = mapper.apply(entry.getValue());

            if (r == null) {
                continue;
            }

            list.add(r);
        }

        return list;
    }

    public static <K, V, R> List<R> toList(Map<K, V> map, BiFunction<V, K, R> mapper) {
        if (EmptyUtils.isEmpty(map)) {
            return EmptyUtils.emptyList();
        }

        List<R> list = new ArrayList<>(map.size());

        for (Map.Entry<K, V> entry : map.entrySet()) {
            R r = mapper.apply(entry.getValue(), entry.getKey());

            if (r == null) {
                continue;
            }

            list.add(r);
        }

        return list;
    }

    public static <T, K> Map<K, T> toMap(Collection<T> collection, Function<T, K> key) {
        return ListUtils.toMap(collection, key, Function.identity());
    }

    public static <T, K, V> Map<K, V> toMap(Collection<T> collection, Function<T, K> key, Function<T, V> value) {
        if (EmptyUtils.isEmpty(collection)) {
            return EmptyUtils.emptyMap();
        }

        Map<K, V> map = new HashMap<>(collection.size());

        for (T t : collection) {
            K k = key.apply(t);

            if (k == null) {
                continue;
            }

            V v = value.apply(t);

            if (v == null) {
                continue;
            }

            map.put(k, v);
        }

        return map;
    }

    public static <T, K> Map<K, List<T>> toMapList(Collection<T> collection, Function<T, K> key) {
        return ListUtils.toMapList(collection, key, Function.identity());
    }

    public static <T, K, V> Map<K, List<V>> toMapList(Collection<T> collection, Function<T, K> key, Function<T, V> value) {
        if (EmptyUtils.isEmpty(collection)) {
            return EmptyUtils.emptyMap();
        }

        Map<K, List<V>> map = new HashMap<>(collection.size());

        for (T t : collection) {
            K k = key.apply(t);

            if (k == null) {
                continue;
            }

            if (!map.containsKey(k)) {
                map.put(k, new ArrayList<>());
            }

            V v = value.apply(t);

            if (v == null) {
                continue;
            }

            map.get(k).add(v);
        }

        return map;
    }

    public static <T, K, R> List<R> toTree(Collection<T> collection, Function<T, K> key, Function<T, K> parent, Function<R, Collection<R>> children, Function<T, R> mapper) {
        List<R> parents = new ArrayList<>();

        Map<K, R> parentMap = ListUtils.toMap(collection, key, item -> {
                    R r = mapper.apply(item);

                    if (!EmptyUtils.hasValue(parent.apply(item))) {
                        parents.add(r);
                    }

                    return r;
                });

        for (T t : collection) {
            K k = parent.apply(t);

            if (!EmptyUtils.hasValue(k)) {
                continue;
            }

            R r = parentMap.get(k);

            if (r == null) {
                continue;
            }

            children.apply(r).add(parentMap.get(key.apply(t)));
        }

        return parents;
    }

    public static <T> List<List<T>> cartesian(List<List<T>> lists) {
        List<List<T>> resultLists = new ArrayList<>();

        if (lists == null || lists.isEmpty()) {
            return resultLists;
        }

        resultLists.add(new ArrayList<>());

        for (List<T> list : lists) {
            List<List<T>> newResultLists = new ArrayList<>();

            for (List<T> existingList : resultLists) {
                for (T element : list) {
                    List<T> newList = new ArrayList<>(existingList);

                    newList.add(element);

                    newResultLists.add(newList);
                }
            }

            resultLists = newResultLists;
        }

        return resultLists;
    }


}

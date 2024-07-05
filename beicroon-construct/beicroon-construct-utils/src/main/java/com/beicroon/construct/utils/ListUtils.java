package com.beicroon.construct.utils;

import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public final class ListUtils {

    private ListUtils() {

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

    public static <T, R> String join(Collection<T> collection, Function<? super T, ? extends R> key, String separator) {
        return ListUtils.join(ListUtils.toSet(collection, key), separator);
    }

    public static String join(Collection<?> collection, String separator) {
        if (EmptyUtils.isEmpty(collection)) {
            return EmptyUtils.emptyString();
        }

        Iterator<?> iterator = collection.iterator();

        StringBuilder sb = new StringBuilder();

        while (iterator.hasNext()) {
            Object value = iterator.next();

            if (value != null) {
                sb.append(value);
            }

            if (iterator.hasNext()) {
                sb.append(separator);
            }
        }

        return sb.toString();
    }

    public static List<String> split(String value, String regex) {
        return ListUtils.split(value, regex, 0);
    }

    public static List<String> split(String value, String regex, int limit) {
        if (EmptyUtils.isEmpty(value)) {
            return EmptyUtils.emptyList();
        }

        return Arrays.asList(value.split(regex, limit));
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

    public static <T, R> Set<R> toSet(Collection<T> collection, Function<? super T, ? extends R> key) {
        if (EmptyUtils.isEmpty(collection)) {
            return EmptyUtils.emptySet();
        }

        Set<R> set = new HashSet<>(collection.size());

        for (T t : collection) {
            R r = key.apply(t);

            if (r == null) {
                continue;
            }

            set.add(r);
        }

        return set;
    }

    public static <T, R> List<R> toList(Collection<T> collection, Function<? super T, ? extends R> key) {
        if (EmptyUtils.isEmpty(collection)) {
            return EmptyUtils.emptyList();
        }

        List<R> list = new ArrayList<>(collection.size());

        for (T t : collection) {
            R r = key.apply(t);

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

}

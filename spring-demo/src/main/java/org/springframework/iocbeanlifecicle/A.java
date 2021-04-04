package org.springframework.iocbeanlifecicle;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

public class A {
	public static void main(String[] args) {
		// ============== new三条源数据，value值均为一个字，加入list ==================
		Map<String, Object> map1 = new HashMap<>();
		map1.put("id", "1");
		map1.put("ab", "甲");
		map1.put("ac", "乙");
		Map<String, Object> map2 = new HashMap<>();
		map2.put("id", "2");
		map2.put("ab", "丙");
		map2.put("ac", "丁");
		Map<String, Object> map3 = new HashMap<>();
		map3.put("id", "3");
		map3.put("ab", "小果");
		map3.put("ac", null);

		List<Map<String, Object>> sourceList = new ArrayList<>();
		sourceList.add(map1);
		sourceList.add(map2);
		sourceList.add(map3);

		// ============== new三条新带并入的数据，value值均为两个字，加入list ==================
		Map<String, Object> newMap1 = new HashMap<>();
		newMap1.put("id", "1");
		newMap1.put("ww", "小强");
		newMap1.put("nn", "小张");
		Map<String, Object> newMap2 = new HashMap<>();
		newMap2.put("id", "2");
		newMap2.put("ww", "王红");
		newMap2.put("nn", "王亮");
//		Map<String, Object> newMap3 = new HashMap<>();
//		newMap3.put("id", "3");
//		newMap3.put("ww", "朱大");
//		newMap3.put("nn", "朱二");

		List<Map<String, Object>> newList = new ArrayList<>();
		newList.add(newMap1);
		newList.add(newMap2);
//		newList.add(newMap3);
		List<Map<String,Object>> combine = merge(sourceList,newList,"id");

		// 输出测试，
		combine.forEach(System.err::println);
	}

		public static List<Map<String, Object>> merge(List<Map<String, Object>> m1, List<Map<String, Object>> m2,String mergeKey){

			m1.addAll(m2);

			Set<String> set = new HashSet<>();

			return m1.stream()
					.collect(Collectors.groupingBy(o->{
						//暂存所有key
						set.addAll(o.keySet());
						//按mergeKey分组
						return o.get(mergeKey);
					})).entrySet().stream().map(o->{
						//合并
						Map<String, Object> map = o.getValue().stream().flatMap(m->{
							return m.entrySet().stream();
						}).collect(Collectors.toMap(Map.Entry::getKey, t->t.getValue() == null?"":t.getValue(), (a,b)->b));
						//为没有的key赋值0
						set.stream().forEach(k->{
							if(!map.containsKey(k)) {
								map.put(k, BigDecimal.ZERO.setScale(2,BigDecimal.ROUND_HALF_UP).toString());
							}
						});

						return map;
					}).collect(Collectors.toList());
		}


}

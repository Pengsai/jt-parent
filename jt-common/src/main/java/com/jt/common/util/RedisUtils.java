package com.jt.common.util;


/**
 * Redis工具接口
 * 单机版
 * @author ps
 * @data 2018年06月14日
 * @version V1.0
 */
public interface RedisUtils {
    /**
     * 保存
     *  @param key
     * 键
     * @param value
     */
    public String set(String key, String value);

    /**
     * 保存并设置生存时间
     *
     * @param key
     * 键
     * @param value
     * 值
     * @param seconds
     * 时间，秒s为单位
     */
    public void set(String key, String value, Integer seconds);

    /**
     * 根据key查询
     *
     * @param key
     * 键
     * @return 值
     */
    public String get(String key);

    /**
     * 删除
     *
     * @param key
     * 键
     */
    public void del(String key);

    /**
     * 根据key设置生存时间
     *
     * @param key
     * 键
     * @param seconds
     * 时间，秒s为单位
     */
    public void expire(String key, Integer seconds);

    /**
     * value加一<br/>
     * 注意key必须是整型
     *
     * @param key
     * 键
     * @return 加一后的结果
     */
    public Long incr(String key);
}
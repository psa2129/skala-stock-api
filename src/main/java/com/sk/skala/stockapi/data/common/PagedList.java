package com.sk.skala.stockapi.data.common;

import java.util.List;

public class PagedList {
    private long total;
    private long count;
    private long offset;
    private Object list;

    // 기본 생성자
    public PagedList() {}

    // 직접 작성한 Setter (롬복 없이도 에러가 안 나게 함)
    public void setTotal(long total) { this.total = total; }
    public void setCount(long count) { this.count = count; }
    public void setOffset(long offset) { this.offset = offset; }
    public void setList(Object list) { this.list = list; }

    // 직접 작성한 Getter
    public long getTotal() { return total; }
    public long getCount() { return count; }
    public long getOffset() { return offset; }
    public Object getList() { return list; }
}
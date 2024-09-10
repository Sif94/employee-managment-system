package org.baouz.employeemanagementsystem.commun;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class PageResponse<T> {
    private int page;
    private int pageSize;
    private long totalElements;
    private long totalPages;
    private List<T> content;
    private boolean isFirst;
    private boolean isLast;
}

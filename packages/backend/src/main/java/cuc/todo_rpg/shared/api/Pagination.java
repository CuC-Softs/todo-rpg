package cuc.todo_rpg.shared.api;

import java.io.Serializable;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Pagination<T> implements Serializable {

    private int pageIndex;

    private int pageSize;

    private long totalCount;

    private List<T> items;
}
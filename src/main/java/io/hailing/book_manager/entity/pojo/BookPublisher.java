package io.hailing.book_manager.entity.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 *
 * </p>
 *
 * @author testjava
 * @since 2023-10-19
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="BookPublisher对象", description="")
public class BookPublisher implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "book_id", type = IdType.ID_WORKER_STR)
    private Integer bookId;

    private Integer publisherId;
}

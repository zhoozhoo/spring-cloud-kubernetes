package ca.zhoozhoo.spring.cloud.kubernetes.rooms.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import lombok.Data;

@Table
@Data
public class Room {

    @Id
    @Column("ROOM_ID")
    private long id;

    private String name;

    private String roomNumber;

    private String bedInfo;
}

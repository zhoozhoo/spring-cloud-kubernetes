package ca.zhoozhoo.lil.roomservices;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "ROOM")
@Data
public class Room {

    @Id
    @Column(name = "ROOM_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "ROOM_NUMBER")
    private String roomNumber;

    @Column(name = "BED_INFO")
    private String bedInfo;
}

package com.jaytech.springbootjpa.domain;

import com.jaytech.springbootjpa.Phone;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "mydata")
@NamedQueries({
        @NamedQuery(
                name = "findWithName",
                query = "from MyData where name like :fname"
        ),
        @NamedQuery(
                name = "findByAge",
                query = "from MyData where age between :min and :max"
        )
})
public class MyData {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    @NotNull
    private long id;

    @Column(length = 50, nullable = false)
    @NotEmpty
    private String name;

    @Column(length = 200, nullable = true)
    @NotEmpty
    private String mail;

    @Column(nullable = true)
    @Min(value = 0)
    @Max(value = 150)
    private Integer age;

    @Column(nullable = true)
    @Phone
    // @Phone(onlyNumber = true) custom한 validator를 설정 할 수 있다.
    private String memo;

    @OneToMany(cascade = CascadeType.ALL)
    @Column(nullable = true)
    private List<MsgData> msgData;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public List<MsgData> getMsgData() {
        return msgData;
    }

    public void setMsgData(List<MsgData> msgData) {
        this.msgData = msgData;
    }
}

package com.ztc.testcenter.domain.test;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yubar on 3/2/2017.
 */

@Entity
@Table(name = "TEST_TEMPLATES")
public class TestTemplate implements Serializable {

    private Long id;
    private List<TestTemplateItem> items = new ArrayList<>();

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @OrderBy("number")
    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
    @JoinColumn(name = "test_template")
    public List<TestTemplateItem> getItems() {
        return items;
    }

    public void setItems(List<TestTemplateItem> items) {
        this.items = items;
    }
}

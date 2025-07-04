package org.example.assignment.domain.category;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private Category parent;

    @OneToMany(mappedBy = "parent",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<Category> children = new ArrayList<>();

    // 카테고리 명
    @Column
    private String name;


    public void addChild(Category child) {
        this.children.add(child);
        child.parent=this;
    }

    public void removeChild(Category child) {
        children.remove(child);
        child.parent = null;
        System.out.println();
    }
}

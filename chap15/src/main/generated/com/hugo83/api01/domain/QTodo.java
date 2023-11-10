package com.hugo83.api01.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QTodo is a Querydsl query type for Todo
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QTodo extends EntityPathBase<Todo> {

    private static final long serialVersionUID = -597157556L;

    public static final QTodo todo = new QTodo("todo");

    public final BooleanPath complete = createBoolean("complete");

    public final DatePath<java.time.LocalDate> dueDate = createDate("dueDate", java.time.LocalDate.class);

    public final StringPath title = createString("title");

    public final NumberPath<Long> tno = createNumber("tno", Long.class);

    public final StringPath writer = createString("writer");

    public QTodo(String variable) {
        super(Todo.class, forVariable(variable));
    }

    public QTodo(Path<? extends Todo> path) {
        super(path.getType(), path.getMetadata());
    }

    public QTodo(PathMetadata metadata) {
        super(Todo.class, metadata);
    }

}


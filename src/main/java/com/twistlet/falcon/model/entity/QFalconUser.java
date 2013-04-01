package com.twistlet.falcon.model.entity;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QFalconUser is a Querydsl query type for FalconUser
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QFalconUser extends EntityPathBase<FalconUser> {

    private static final long serialVersionUID = -115658286;

    public static final QFalconUser falconUser = new QFalconUser("falconUser");

    public final StringPath email = createString("email");

    public final SetPath<FalconAppointment, QFalconAppointment> falconAppointments = this.<FalconAppointment, QFalconAppointment>createSet("falconAppointments", FalconAppointment.class, QFalconAppointment.class, PathInits.DIRECT);

    public final SetPath<FalconPatron, QFalconPatron> falconPatrons = this.<FalconPatron, QFalconPatron>createSet("falconPatrons", FalconPatron.class, QFalconPatron.class, PathInits.DIRECT);

    public final SetPath<FalconUserRole, QFalconUserRole> falconUserRoles = this.<FalconUserRole, QFalconUserRole>createSet("falconUserRoles", FalconUserRole.class, QFalconUserRole.class, PathInits.DIRECT);

    public final StringPath name = createString("name");

    public final StringPath password = createString("password");

    public final StringPath phone = createString("phone");

    public final StringPath username = createString("username");

    public QFalconUser(String variable) {
        super(FalconUser.class, forVariable(variable));
    }

    @SuppressWarnings("all")
    public QFalconUser(Path<? extends FalconUser> path) {
        super((Class)path.getType(), path.getMetadata());
    }

    public QFalconUser(PathMetadata<?> metadata) {
        super(FalconUser.class, metadata);
    }

}


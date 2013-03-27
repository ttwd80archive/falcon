package com.twistlet.falcon.model.entity;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QFalconLocation is a Querydsl query type for FalconLocation
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QFalconLocation extends EntityPathBase<FalconLocation> {

    private static final long serialVersionUID = 855870012;

    public static final QFalconLocation falconLocation = new QFalconLocation("falconLocation");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath name = createString("name");

    public QFalconLocation(String variable) {
        super(FalconLocation.class, forVariable(variable));
    }

    @SuppressWarnings("all")
    public QFalconLocation(Path<? extends FalconLocation> path) {
        super((Class)path.getType(), path.getMetadata());
    }

    public QFalconLocation(PathMetadata<?> metadata) {
        super(FalconLocation.class, metadata);
    }

}


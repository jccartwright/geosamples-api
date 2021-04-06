package noaa.ncei.ogssd.geosamples.repository

import org.springframework.stereotype.Repository

/*
 * interface to database table(s).
 * WARNING: this class has a tight coupling to the underlying database schema
 *
 */

@Repository
class StorageMethodRepository extends BaseRepository {
    static final String TABLENAME = 'curators_sample_tsqp'
    static final String recordsQueryString =
        "select distinct storage_meth from ${SCHEMA}.${TABLENAME}"
    static final String countQueryString =
        "select count(distinct storage_meth) from ${SCHEMA}.${TABLENAME}"
    static final String orderByClause = " order by storage_meth"
    List defaultCriteria = ["storage_meth is not null"]

}

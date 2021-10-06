package fr.istic.taa.jaxrs.Infrastructure.Shared;

import fr.istic.taa.jaxrs.dao.concrete.IndividualDao;

public class GetUserClass {

    private long id;

    public GetUserClass(final long id) {
            this.id = id;
    }

    /**
     *
     * @return true if the id is corresponding to an Individual.
     */
    public boolean isIndividual() {
        return new IndividualDao().findOne(this.id) != null ;
    }


}

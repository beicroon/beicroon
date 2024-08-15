package com.beicroon.starter.generic;

import com.beicroon.starter.generic.maker.ServiceStructureMaker;

public class ProjectGenericApplication {

    public static void main(String[] args) {
        ServiceStructureMaker.generic("beicroon-service-admin", "com.beicroon.service.admin", 8100, 1);
    }

}

package org.example.service;

import org.example.model.Responsable;
import org.example.model.Solicitud;

/** Move Method: la regla "que responsable puede atender que solicitud" vive aca y no en la entidad Solicitud. */
public class AsignacionService {

    public boolean puedeAsignarse(Solicitud solicitud, Responsable responsable) {
        return responsable != null
                && responsable.isActivo()
                && solicitud.getTipo().getAreaResponsable() == responsable.getArea();
    }
}

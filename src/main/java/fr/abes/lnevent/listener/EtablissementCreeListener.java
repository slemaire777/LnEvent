package fr.abes.lnevent.listener;

import fr.abes.lnevent.entities.ContactRow;
import fr.abes.lnevent.entities.EtablissementRow;
import fr.abes.lnevent.repository.ContactRepository;
import fr.abes.lnevent.repository.EtablissementRepository;
import fr.abes.lnevent.event.EtablissementCreeEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class EtablissementCreeListener implements ApplicationListener<EtablissementCreeEvent> {

    private final EtablissementRepository etablissementRepository;
    private final ContactRepository contactRepository;

    public EtablissementCreeListener(EtablissementRepository etablissementRepository,
                                     ContactRepository contactRepository) {
        this.etablissementRepository = etablissementRepository;
        this.contactRepository = contactRepository;
    }

    @Override
    public void onApplicationEvent(EtablissementCreeEvent etablissementCreeEvent) {
        EtablissementRow etablissementRow =
                new EtablissementRow(null,
                etablissementCreeEvent.getNom(),
                etablissementCreeEvent.getAdresse(),
                etablissementCreeEvent.getSiren(),
                etablissementCreeEvent.getTypeEtablissement(),
                etablissementCreeEvent.getMotDePasse(),
                etablissementCreeEvent.getIdAbes());

        etablissementRepository.save(etablissementRow);

        ContactRow contactRow =
                new ContactRow(null,
                        etablissementCreeEvent.getNomContact(),
                        etablissementCreeEvent.getPrenomContact(),
                        etablissementCreeEvent.getMailContact(),
                        etablissementCreeEvent.getTelephoneContact(),
                        etablissementCreeEvent.getAdresseContact(),
                        etablissementRow.id);

        contactRepository.save(contactRow);

    }
}

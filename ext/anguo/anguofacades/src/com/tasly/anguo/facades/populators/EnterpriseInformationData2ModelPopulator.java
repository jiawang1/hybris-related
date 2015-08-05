package com.tasly.anguo.facades.populators;

import java.util.HashSet;
import java.util.Set;

import com.tasly.anguo.core.model.ContactModel;
import com.tasly.anguo.core.model.EnterpriseAccountModel;
import com.tasly.anguo.facades.converters.ContactData2ModelConverter;
import com.tasly.anguo.facades.user.data.ContactData;
import com.tasly.anguo.facades.user.data.EnterpriseInformationData;

import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

public class EnterpriseInformationData2ModelPopulator implements Populator<EnterpriseInformationData, EnterpriseAccountModel>{

    @Override
    public void populate(EnterpriseInformationData source,
            EnterpriseAccountModel target) throws ConversionException {
        target.setCompanyName(source.getName());
        target.setEnterpriseAddress(source.getAddress());
        Set<ContactModel> list = new HashSet<ContactModel>();
        if (source.getContacts() != null) {
            ContactData2ModelConverter converter = new ContactData2ModelConverter();
            for (ContactData cd : source.getContacts()) {
                ContactModel cm = converter.convert(cd);
                cm.setOwner(target);
                list.add(cm);
            }
        }
        target.setContacts(list);
        target.setEnterprisePhone(source.getPhone());
        target.setEnterpriseFax(source.getFax());
        target.setRegisteredNo(source.getRegisterId());
    }

}

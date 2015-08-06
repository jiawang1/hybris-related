package com.tasly.anguo.facades.populators;

import java.util.ArrayList;
import java.util.List;

import com.tasly.anguo.core.model.ContactModel;
import com.tasly.anguo.core.model.EnterpriseAccountModel;
import com.tasly.anguo.facades.converters.ContactConverter;
import com.tasly.anguo.facades.user.data.ContactData;
import com.tasly.anguo.facades.user.data.EnterpriseInformationData;

import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

public class EnterpriseInformationPopulator implements Populator<EnterpriseAccountModel, EnterpriseInformationData>{

    @Override
    public void populate(EnterpriseAccountModel source,
            EnterpriseInformationData target) throws ConversionException {
        target.setName(source.getCompanyName());
        target.setAddress(source.getEnterpriseAddress());
        target.setFax(source.getEnterpriseFax());
        target.setPhone(source.getEnterprisePhone());
        target.setRegisterId(source.getRegisteredNo());

        List<ContactData> list = new ArrayList<ContactData>();
        if (source.getContacts() != null) {
            ContactConverter converter = new ContactConverter();
            for (ContactModel cm : source.getContacts()) {
                ContactData cd = converter.convert(cm);
                list.add(cd);
            }
        }
        target.setContacts(list);
    }

}

package com.tasly.anguo.facades.populators;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Required;

import com.tasly.anguo.core.model.ContactModel;
import com.tasly.anguo.core.model.EnterpriseAccountModel;
import com.tasly.anguo.facades.converters.ContactReverseConverter;
import com.tasly.anguo.facades.user.data.ContactData;
import com.tasly.anguo.facades.user.data.EnterpriseInformationData;

import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

public class EnterpriseInformationReversePopulator implements Populator<EnterpriseInformationData, EnterpriseAccountModel>{
    
    ContactReverseConverter contactReverseConverter;

    public ContactReverseConverter getContactReverseConverter() {
        return contactReverseConverter;
    }
    
    @Required
    public void setContactReverseConverter(
            ContactReverseConverter contactReverseConverter) {
        this.contactReverseConverter = contactReverseConverter;
    }

    @Override
    public void populate(EnterpriseInformationData source,
            EnterpriseAccountModel target) throws ConversionException {
        target.setCompanyName(source.getName());
        target.setEnterpriseAddress(source.getAddress());
        Set<ContactModel> list = new HashSet<ContactModel>();
        if (source.getContacts() != null) {
            for (ContactData cd : source.getContacts()) {
                ContactModel cm = contactReverseConverter.convert(cd);
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

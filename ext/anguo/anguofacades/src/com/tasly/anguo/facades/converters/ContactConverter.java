package com.tasly.anguo.facades.converters;

import com.tasly.anguo.core.model.ContactModel;
import com.tasly.anguo.facades.user.data.ContactData;

import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import de.hybris.platform.servicelayer.dto.converter.Converter;

public class ContactConverter implements Converter<ContactModel, ContactData>{

    @Override
    public ContactData convert(ContactModel arg0) throws ConversionException {
        ContactData model = new ContactData();
        convert(arg0, model);
        return model;
    }

    @Override
    public ContactData convert(ContactModel arg0, ContactData arg1)
            throws ConversionException {
        arg1.setName(arg0.getName());
        arg1.setContactInfo(arg0.getContactInfo());
        arg1.setPosition(arg0.getPosition());
        if (arg0.getPk() != null)
            arg1.setId(arg0.getPk().toString());
        return arg1;
    }

}

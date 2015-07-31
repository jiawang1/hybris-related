package com.tasly.anguo.core.jalo;

import com.tasly.anguo.core.constants.AnguoCoreConstants;
import de.hybris.platform.jalo.JaloSession;
import de.hybris.platform.jalo.extension.ExtensionManager;
import org.apache.log4j.Logger;

@SuppressWarnings("PMD")
public class AnguoCoreManager extends GeneratedAnguoCoreManager
{
	@SuppressWarnings("unused")
	private static Logger log = Logger.getLogger( AnguoCoreManager.class.getName() );
	
	public static final AnguoCoreManager getInstance()
	{
		ExtensionManager em = JaloSession.getCurrentSession().getExtensionManager();
		return (AnguoCoreManager) em.getExtension(AnguoCoreConstants.EXTENSIONNAME);
	}
	
}

/**
 */
package com.project.mde.ui.impl;

import com.project.mde.ui.TaskAndDomainModel;
import com.project.mde.ui.UiPackage;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Task And Domain Model</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link com.project.mde.ui.impl.TaskAndDomainModelImpl#getActivityId <em>Activity Id</em>}</li>
 *   <li>{@link com.project.mde.ui.impl.TaskAndDomainModelImpl#getConceptId <em>Concept Id</em>}</li>
 *   <li>{@link com.project.mde.ui.impl.TaskAndDomainModelImpl#isRequiresEditor <em>Requires Editor</em>}</li>
 *   <li>{@link com.project.mde.ui.impl.TaskAndDomainModelImpl#isRequiresSimulator <em>Requires Simulator</em>}</li>
 *   <li>{@link com.project.mde.ui.impl.TaskAndDomainModelImpl#isSupportsCodeView <em>Supports Code View</em>}</li>
 *   <li>{@link com.project.mde.ui.impl.TaskAndDomainModelImpl#isSupportsHints <em>Supports Hints</em>}</li>
 *   <li>{@link com.project.mde.ui.impl.TaskAndDomainModelImpl#isSupportsFeedback <em>Supports Feedback</em>}</li>
 *   <li>{@link com.project.mde.ui.impl.TaskAndDomainModelImpl#isSupportsNavigation <em>Supports Navigation</em>}</li>
 *   <li>{@link com.project.mde.ui.impl.TaskAndDomainModelImpl#isSupportsTutor <em>Supports Tutor</em>}</li>
 * </ul>
 *
 * @generated
 */
public class TaskAndDomainModelImpl extends MinimalEObjectImpl.Container implements TaskAndDomainModel {
	/**
	 * The default value of the '{@link #getActivityId() <em>Activity Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getActivityId()
	 * @generated
	 * @ordered
	 */
	protected static final String ACTIVITY_ID_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getActivityId() <em>Activity Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getActivityId()
	 * @generated
	 * @ordered
	 */
	protected String activityId = ACTIVITY_ID_EDEFAULT;

	/**
	 * The default value of the '{@link #getConceptId() <em>Concept Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getConceptId()
	 * @generated
	 * @ordered
	 */
	protected static final String CONCEPT_ID_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getConceptId() <em>Concept Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getConceptId()
	 * @generated
	 * @ordered
	 */
	protected String conceptId = CONCEPT_ID_EDEFAULT;

	/**
	 * The default value of the '{@link #isRequiresEditor() <em>Requires Editor</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isRequiresEditor()
	 * @generated
	 * @ordered
	 */
	protected static final boolean REQUIRES_EDITOR_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isRequiresEditor() <em>Requires Editor</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isRequiresEditor()
	 * @generated
	 * @ordered
	 */
	protected boolean requiresEditor = REQUIRES_EDITOR_EDEFAULT;

	/**
	 * The default value of the '{@link #isRequiresSimulator() <em>Requires Simulator</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isRequiresSimulator()
	 * @generated
	 * @ordered
	 */
	protected static final boolean REQUIRES_SIMULATOR_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isRequiresSimulator() <em>Requires Simulator</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isRequiresSimulator()
	 * @generated
	 * @ordered
	 */
	protected boolean requiresSimulator = REQUIRES_SIMULATOR_EDEFAULT;

	/**
	 * The default value of the '{@link #isSupportsCodeView() <em>Supports Code View</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSupportsCodeView()
	 * @generated
	 * @ordered
	 */
	protected static final boolean SUPPORTS_CODE_VIEW_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isSupportsCodeView() <em>Supports Code View</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSupportsCodeView()
	 * @generated
	 * @ordered
	 */
	protected boolean supportsCodeView = SUPPORTS_CODE_VIEW_EDEFAULT;

	/**
	 * The default value of the '{@link #isSupportsHints() <em>Supports Hints</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSupportsHints()
	 * @generated
	 * @ordered
	 */
	protected static final boolean SUPPORTS_HINTS_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isSupportsHints() <em>Supports Hints</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSupportsHints()
	 * @generated
	 * @ordered
	 */
	protected boolean supportsHints = SUPPORTS_HINTS_EDEFAULT;

	/**
	 * The default value of the '{@link #isSupportsFeedback() <em>Supports Feedback</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSupportsFeedback()
	 * @generated
	 * @ordered
	 */
	protected static final boolean SUPPORTS_FEEDBACK_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isSupportsFeedback() <em>Supports Feedback</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSupportsFeedback()
	 * @generated
	 * @ordered
	 */
	protected boolean supportsFeedback = SUPPORTS_FEEDBACK_EDEFAULT;

	/**
	 * The default value of the '{@link #isSupportsNavigation() <em>Supports Navigation</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSupportsNavigation()
	 * @generated
	 * @ordered
	 */
	protected static final boolean SUPPORTS_NAVIGATION_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isSupportsNavigation() <em>Supports Navigation</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSupportsNavigation()
	 * @generated
	 * @ordered
	 */
	protected boolean supportsNavigation = SUPPORTS_NAVIGATION_EDEFAULT;

	/**
	 * The default value of the '{@link #isSupportsTutor() <em>Supports Tutor</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSupportsTutor()
	 * @generated
	 * @ordered
	 */
	protected static final boolean SUPPORTS_TUTOR_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isSupportsTutor() <em>Supports Tutor</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSupportsTutor()
	 * @generated
	 * @ordered
	 */
	protected boolean supportsTutor = SUPPORTS_TUTOR_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TaskAndDomainModelImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return UiPackage.Literals.TASK_AND_DOMAIN_MODEL;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getActivityId() {
		return activityId;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setActivityId(String newActivityId) {
		String oldActivityId = activityId;
		activityId = newActivityId;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, UiPackage.TASK_AND_DOMAIN_MODEL__ACTIVITY_ID, oldActivityId, activityId));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getConceptId() {
		return conceptId;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setConceptId(String newConceptId) {
		String oldConceptId = conceptId;
		conceptId = newConceptId;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, UiPackage.TASK_AND_DOMAIN_MODEL__CONCEPT_ID, oldConceptId, conceptId));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isRequiresEditor() {
		return requiresEditor;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setRequiresEditor(boolean newRequiresEditor) {
		boolean oldRequiresEditor = requiresEditor;
		requiresEditor = newRequiresEditor;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, UiPackage.TASK_AND_DOMAIN_MODEL__REQUIRES_EDITOR, oldRequiresEditor, requiresEditor));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isRequiresSimulator() {
		return requiresSimulator;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setRequiresSimulator(boolean newRequiresSimulator) {
		boolean oldRequiresSimulator = requiresSimulator;
		requiresSimulator = newRequiresSimulator;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, UiPackage.TASK_AND_DOMAIN_MODEL__REQUIRES_SIMULATOR, oldRequiresSimulator, requiresSimulator));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isSupportsCodeView() {
		return supportsCodeView;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setSupportsCodeView(boolean newSupportsCodeView) {
		boolean oldSupportsCodeView = supportsCodeView;
		supportsCodeView = newSupportsCodeView;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, UiPackage.TASK_AND_DOMAIN_MODEL__SUPPORTS_CODE_VIEW, oldSupportsCodeView, supportsCodeView));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isSupportsHints() {
		return supportsHints;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setSupportsHints(boolean newSupportsHints) {
		boolean oldSupportsHints = supportsHints;
		supportsHints = newSupportsHints;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, UiPackage.TASK_AND_DOMAIN_MODEL__SUPPORTS_HINTS, oldSupportsHints, supportsHints));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isSupportsFeedback() {
		return supportsFeedback;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setSupportsFeedback(boolean newSupportsFeedback) {
		boolean oldSupportsFeedback = supportsFeedback;
		supportsFeedback = newSupportsFeedback;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, UiPackage.TASK_AND_DOMAIN_MODEL__SUPPORTS_FEEDBACK, oldSupportsFeedback, supportsFeedback));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isSupportsNavigation() {
		return supportsNavigation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setSupportsNavigation(boolean newSupportsNavigation) {
		boolean oldSupportsNavigation = supportsNavigation;
		supportsNavigation = newSupportsNavigation;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, UiPackage.TASK_AND_DOMAIN_MODEL__SUPPORTS_NAVIGATION, oldSupportsNavigation, supportsNavigation));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isSupportsTutor() {
		return supportsTutor;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setSupportsTutor(boolean newSupportsTutor) {
		boolean oldSupportsTutor = supportsTutor;
		supportsTutor = newSupportsTutor;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, UiPackage.TASK_AND_DOMAIN_MODEL__SUPPORTS_TUTOR, oldSupportsTutor, supportsTutor));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case UiPackage.TASK_AND_DOMAIN_MODEL__ACTIVITY_ID:
				return getActivityId();
			case UiPackage.TASK_AND_DOMAIN_MODEL__CONCEPT_ID:
				return getConceptId();
			case UiPackage.TASK_AND_DOMAIN_MODEL__REQUIRES_EDITOR:
				return isRequiresEditor();
			case UiPackage.TASK_AND_DOMAIN_MODEL__REQUIRES_SIMULATOR:
				return isRequiresSimulator();
			case UiPackage.TASK_AND_DOMAIN_MODEL__SUPPORTS_CODE_VIEW:
				return isSupportsCodeView();
			case UiPackage.TASK_AND_DOMAIN_MODEL__SUPPORTS_HINTS:
				return isSupportsHints();
			case UiPackage.TASK_AND_DOMAIN_MODEL__SUPPORTS_FEEDBACK:
				return isSupportsFeedback();
			case UiPackage.TASK_AND_DOMAIN_MODEL__SUPPORTS_NAVIGATION:
				return isSupportsNavigation();
			case UiPackage.TASK_AND_DOMAIN_MODEL__SUPPORTS_TUTOR:
				return isSupportsTutor();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case UiPackage.TASK_AND_DOMAIN_MODEL__ACTIVITY_ID:
				setActivityId((String)newValue);
				return;
			case UiPackage.TASK_AND_DOMAIN_MODEL__CONCEPT_ID:
				setConceptId((String)newValue);
				return;
			case UiPackage.TASK_AND_DOMAIN_MODEL__REQUIRES_EDITOR:
				setRequiresEditor((Boolean)newValue);
				return;
			case UiPackage.TASK_AND_DOMAIN_MODEL__REQUIRES_SIMULATOR:
				setRequiresSimulator((Boolean)newValue);
				return;
			case UiPackage.TASK_AND_DOMAIN_MODEL__SUPPORTS_CODE_VIEW:
				setSupportsCodeView((Boolean)newValue);
				return;
			case UiPackage.TASK_AND_DOMAIN_MODEL__SUPPORTS_HINTS:
				setSupportsHints((Boolean)newValue);
				return;
			case UiPackage.TASK_AND_DOMAIN_MODEL__SUPPORTS_FEEDBACK:
				setSupportsFeedback((Boolean)newValue);
				return;
			case UiPackage.TASK_AND_DOMAIN_MODEL__SUPPORTS_NAVIGATION:
				setSupportsNavigation((Boolean)newValue);
				return;
			case UiPackage.TASK_AND_DOMAIN_MODEL__SUPPORTS_TUTOR:
				setSupportsTutor((Boolean)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case UiPackage.TASK_AND_DOMAIN_MODEL__ACTIVITY_ID:
				setActivityId(ACTIVITY_ID_EDEFAULT);
				return;
			case UiPackage.TASK_AND_DOMAIN_MODEL__CONCEPT_ID:
				setConceptId(CONCEPT_ID_EDEFAULT);
				return;
			case UiPackage.TASK_AND_DOMAIN_MODEL__REQUIRES_EDITOR:
				setRequiresEditor(REQUIRES_EDITOR_EDEFAULT);
				return;
			case UiPackage.TASK_AND_DOMAIN_MODEL__REQUIRES_SIMULATOR:
				setRequiresSimulator(REQUIRES_SIMULATOR_EDEFAULT);
				return;
			case UiPackage.TASK_AND_DOMAIN_MODEL__SUPPORTS_CODE_VIEW:
				setSupportsCodeView(SUPPORTS_CODE_VIEW_EDEFAULT);
				return;
			case UiPackage.TASK_AND_DOMAIN_MODEL__SUPPORTS_HINTS:
				setSupportsHints(SUPPORTS_HINTS_EDEFAULT);
				return;
			case UiPackage.TASK_AND_DOMAIN_MODEL__SUPPORTS_FEEDBACK:
				setSupportsFeedback(SUPPORTS_FEEDBACK_EDEFAULT);
				return;
			case UiPackage.TASK_AND_DOMAIN_MODEL__SUPPORTS_NAVIGATION:
				setSupportsNavigation(SUPPORTS_NAVIGATION_EDEFAULT);
				return;
			case UiPackage.TASK_AND_DOMAIN_MODEL__SUPPORTS_TUTOR:
				setSupportsTutor(SUPPORTS_TUTOR_EDEFAULT);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case UiPackage.TASK_AND_DOMAIN_MODEL__ACTIVITY_ID:
				return ACTIVITY_ID_EDEFAULT == null ? activityId != null : !ACTIVITY_ID_EDEFAULT.equals(activityId);
			case UiPackage.TASK_AND_DOMAIN_MODEL__CONCEPT_ID:
				return CONCEPT_ID_EDEFAULT == null ? conceptId != null : !CONCEPT_ID_EDEFAULT.equals(conceptId);
			case UiPackage.TASK_AND_DOMAIN_MODEL__REQUIRES_EDITOR:
				return requiresEditor != REQUIRES_EDITOR_EDEFAULT;
			case UiPackage.TASK_AND_DOMAIN_MODEL__REQUIRES_SIMULATOR:
				return requiresSimulator != REQUIRES_SIMULATOR_EDEFAULT;
			case UiPackage.TASK_AND_DOMAIN_MODEL__SUPPORTS_CODE_VIEW:
				return supportsCodeView != SUPPORTS_CODE_VIEW_EDEFAULT;
			case UiPackage.TASK_AND_DOMAIN_MODEL__SUPPORTS_HINTS:
				return supportsHints != SUPPORTS_HINTS_EDEFAULT;
			case UiPackage.TASK_AND_DOMAIN_MODEL__SUPPORTS_FEEDBACK:
				return supportsFeedback != SUPPORTS_FEEDBACK_EDEFAULT;
			case UiPackage.TASK_AND_DOMAIN_MODEL__SUPPORTS_NAVIGATION:
				return supportsNavigation != SUPPORTS_NAVIGATION_EDEFAULT;
			case UiPackage.TASK_AND_DOMAIN_MODEL__SUPPORTS_TUTOR:
				return supportsTutor != SUPPORTS_TUTOR_EDEFAULT;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuilder result = new StringBuilder(super.toString());
		result.append(" (activityId: ");
		result.append(activityId);
		result.append(", conceptId: ");
		result.append(conceptId);
		result.append(", requiresEditor: ");
		result.append(requiresEditor);
		result.append(", requiresSimulator: ");
		result.append(requiresSimulator);
		result.append(", supportsCodeView: ");
		result.append(supportsCodeView);
		result.append(", supportsHints: ");
		result.append(supportsHints);
		result.append(", supportsFeedback: ");
		result.append(supportsFeedback);
		result.append(", supportsNavigation: ");
		result.append(supportsNavigation);
		result.append(", supportsTutor: ");
		result.append(supportsTutor);
		result.append(')');
		return result.toString();
	}

} //TaskAndDomainModelImpl

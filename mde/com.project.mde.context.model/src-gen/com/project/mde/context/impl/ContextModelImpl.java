/**
 */
package com.project.mde.context.impl;

import com.project.mde.context.ContextModel;
import com.project.mde.context.ContextPackage;
import com.project.mde.context.EnvironmentContext;
import com.project.mde.context.PlatformContext;
import com.project.mde.context.StudentContext;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Model</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link com.project.mde.context.impl.ContextModelImpl#getModelVersion <em>Model Version</em>}</li>
 *   <li>{@link com.project.mde.context.impl.ContextModelImpl#getStudentContext <em>Student Context</em>}</li>
 *   <li>{@link com.project.mde.context.impl.ContextModelImpl#getPlatformContext <em>Platform Context</em>}</li>
 *   <li>{@link com.project.mde.context.impl.ContextModelImpl#getEnvironmentContext <em>Environment Context</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ContextModelImpl extends MinimalEObjectImpl.Container implements ContextModel {
	/**
	 * The default value of the '{@link #getModelVersion() <em>Model Version</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getModelVersion()
	 * @generated
	 * @ordered
	 */
	protected static final int MODEL_VERSION_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getModelVersion() <em>Model Version</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getModelVersion()
	 * @generated
	 * @ordered
	 */
	protected int modelVersion = MODEL_VERSION_EDEFAULT;

	/**
	 * The cached value of the '{@link #getStudentContext() <em>Student Context</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStudentContext()
	 * @generated
	 * @ordered
	 */
	protected StudentContext studentContext;

	/**
	 * The cached value of the '{@link #getPlatformContext() <em>Platform Context</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPlatformContext()
	 * @generated
	 * @ordered
	 */
	protected PlatformContext platformContext;

	/**
	 * The cached value of the '{@link #getEnvironmentContext() <em>Environment Context</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEnvironmentContext()
	 * @generated
	 * @ordered
	 */
	protected EnvironmentContext environmentContext;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ContextModelImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ContextPackage.Literals.CONTEXT_MODEL;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int getModelVersion() {
		return modelVersion;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setModelVersion(int newModelVersion) {
		int oldModelVersion = modelVersion;
		modelVersion = newModelVersion;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ContextPackage.CONTEXT_MODEL__MODEL_VERSION, oldModelVersion, modelVersion));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public StudentContext getStudentContext() {
		return studentContext;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetStudentContext(StudentContext newStudentContext, NotificationChain msgs) {
		StudentContext oldStudentContext = studentContext;
		studentContext = newStudentContext;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ContextPackage.CONTEXT_MODEL__STUDENT_CONTEXT, oldStudentContext, newStudentContext);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setStudentContext(StudentContext newStudentContext) {
		if (newStudentContext != studentContext) {
			NotificationChain msgs = null;
			if (studentContext != null)
				msgs = ((InternalEObject)studentContext).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ContextPackage.CONTEXT_MODEL__STUDENT_CONTEXT, null, msgs);
			if (newStudentContext != null)
				msgs = ((InternalEObject)newStudentContext).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ContextPackage.CONTEXT_MODEL__STUDENT_CONTEXT, null, msgs);
			msgs = basicSetStudentContext(newStudentContext, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ContextPackage.CONTEXT_MODEL__STUDENT_CONTEXT, newStudentContext, newStudentContext));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public PlatformContext getPlatformContext() {
		return platformContext;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetPlatformContext(PlatformContext newPlatformContext, NotificationChain msgs) {
		PlatformContext oldPlatformContext = platformContext;
		platformContext = newPlatformContext;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ContextPackage.CONTEXT_MODEL__PLATFORM_CONTEXT, oldPlatformContext, newPlatformContext);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setPlatformContext(PlatformContext newPlatformContext) {
		if (newPlatformContext != platformContext) {
			NotificationChain msgs = null;
			if (platformContext != null)
				msgs = ((InternalEObject)platformContext).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ContextPackage.CONTEXT_MODEL__PLATFORM_CONTEXT, null, msgs);
			if (newPlatformContext != null)
				msgs = ((InternalEObject)newPlatformContext).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ContextPackage.CONTEXT_MODEL__PLATFORM_CONTEXT, null, msgs);
			msgs = basicSetPlatformContext(newPlatformContext, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ContextPackage.CONTEXT_MODEL__PLATFORM_CONTEXT, newPlatformContext, newPlatformContext));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EnvironmentContext getEnvironmentContext() {
		return environmentContext;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetEnvironmentContext(EnvironmentContext newEnvironmentContext, NotificationChain msgs) {
		EnvironmentContext oldEnvironmentContext = environmentContext;
		environmentContext = newEnvironmentContext;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ContextPackage.CONTEXT_MODEL__ENVIRONMENT_CONTEXT, oldEnvironmentContext, newEnvironmentContext);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setEnvironmentContext(EnvironmentContext newEnvironmentContext) {
		if (newEnvironmentContext != environmentContext) {
			NotificationChain msgs = null;
			if (environmentContext != null)
				msgs = ((InternalEObject)environmentContext).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ContextPackage.CONTEXT_MODEL__ENVIRONMENT_CONTEXT, null, msgs);
			if (newEnvironmentContext != null)
				msgs = ((InternalEObject)newEnvironmentContext).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ContextPackage.CONTEXT_MODEL__ENVIRONMENT_CONTEXT, null, msgs);
			msgs = basicSetEnvironmentContext(newEnvironmentContext, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ContextPackage.CONTEXT_MODEL__ENVIRONMENT_CONTEXT, newEnvironmentContext, newEnvironmentContext));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ContextPackage.CONTEXT_MODEL__STUDENT_CONTEXT:
				return basicSetStudentContext(null, msgs);
			case ContextPackage.CONTEXT_MODEL__PLATFORM_CONTEXT:
				return basicSetPlatformContext(null, msgs);
			case ContextPackage.CONTEXT_MODEL__ENVIRONMENT_CONTEXT:
				return basicSetEnvironmentContext(null, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ContextPackage.CONTEXT_MODEL__MODEL_VERSION:
				return getModelVersion();
			case ContextPackage.CONTEXT_MODEL__STUDENT_CONTEXT:
				return getStudentContext();
			case ContextPackage.CONTEXT_MODEL__PLATFORM_CONTEXT:
				return getPlatformContext();
			case ContextPackage.CONTEXT_MODEL__ENVIRONMENT_CONTEXT:
				return getEnvironmentContext();
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
			case ContextPackage.CONTEXT_MODEL__MODEL_VERSION:
				setModelVersion((Integer)newValue);
				return;
			case ContextPackage.CONTEXT_MODEL__STUDENT_CONTEXT:
				setStudentContext((StudentContext)newValue);
				return;
			case ContextPackage.CONTEXT_MODEL__PLATFORM_CONTEXT:
				setPlatformContext((PlatformContext)newValue);
				return;
			case ContextPackage.CONTEXT_MODEL__ENVIRONMENT_CONTEXT:
				setEnvironmentContext((EnvironmentContext)newValue);
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
			case ContextPackage.CONTEXT_MODEL__MODEL_VERSION:
				setModelVersion(MODEL_VERSION_EDEFAULT);
				return;
			case ContextPackage.CONTEXT_MODEL__STUDENT_CONTEXT:
				setStudentContext((StudentContext)null);
				return;
			case ContextPackage.CONTEXT_MODEL__PLATFORM_CONTEXT:
				setPlatformContext((PlatformContext)null);
				return;
			case ContextPackage.CONTEXT_MODEL__ENVIRONMENT_CONTEXT:
				setEnvironmentContext((EnvironmentContext)null);
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
			case ContextPackage.CONTEXT_MODEL__MODEL_VERSION:
				return modelVersion != MODEL_VERSION_EDEFAULT;
			case ContextPackage.CONTEXT_MODEL__STUDENT_CONTEXT:
				return studentContext != null;
			case ContextPackage.CONTEXT_MODEL__PLATFORM_CONTEXT:
				return platformContext != null;
			case ContextPackage.CONTEXT_MODEL__ENVIRONMENT_CONTEXT:
				return environmentContext != null;
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
		result.append(" (modelVersion: ");
		result.append(modelVersion);
		result.append(')');
		return result.toString();
	}

} //ContextModelImpl

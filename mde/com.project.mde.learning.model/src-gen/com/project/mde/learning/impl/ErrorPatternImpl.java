/**
 */
package com.project.mde.learning.impl;

import com.project.mde.learning.Concept;
import com.project.mde.learning.ErrorPattern;
import com.project.mde.learning.LearningPackage;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Error Pattern</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link com.project.mde.learning.impl.ErrorPatternImpl#getId <em>Id</em>}</li>
 *   <li>{@link com.project.mde.learning.impl.ErrorPatternImpl#getConcept <em>Concept</em>}</li>
 *   <li>{@link com.project.mde.learning.impl.ErrorPatternImpl#getSeverity <em>Severity</em>}</li>
 *   <li>{@link com.project.mde.learning.impl.ErrorPatternImpl#getPedagogicalMeaning <em>Pedagogical Meaning</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ErrorPatternImpl extends MinimalEObjectImpl.Container implements ErrorPattern {
	/**
	 * The default value of the '{@link #getId() <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getId()
	 * @generated
	 * @ordered
	 */
	protected static final String ID_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getId() <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getId()
	 * @generated
	 * @ordered
	 */
	protected String id = ID_EDEFAULT;

	/**
	 * The cached value of the '{@link #getConcept() <em>Concept</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getConcept()
	 * @generated
	 * @ordered
	 */
	protected Concept concept;

	/**
	 * The default value of the '{@link #getSeverity() <em>Severity</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSeverity()
	 * @generated
	 * @ordered
	 */
	protected static final String SEVERITY_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getSeverity() <em>Severity</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSeverity()
	 * @generated
	 * @ordered
	 */
	protected String severity = SEVERITY_EDEFAULT;

	/**
	 * The default value of the '{@link #getPedagogicalMeaning() <em>Pedagogical Meaning</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPedagogicalMeaning()
	 * @generated
	 * @ordered
	 */
	protected static final String PEDAGOGICAL_MEANING_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getPedagogicalMeaning() <em>Pedagogical Meaning</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPedagogicalMeaning()
	 * @generated
	 * @ordered
	 */
	protected String pedagogicalMeaning = PEDAGOGICAL_MEANING_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ErrorPatternImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return LearningPackage.Literals.ERROR_PATTERN;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getId() {
		return id;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setId(String newId) {
		String oldId = id;
		id = newId;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LearningPackage.ERROR_PATTERN__ID, oldId, id));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Concept getConcept() {
		if (concept != null && concept.eIsProxy()) {
			InternalEObject oldConcept = (InternalEObject)concept;
			concept = (Concept)eResolveProxy(oldConcept);
			if (concept != oldConcept) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, LearningPackage.ERROR_PATTERN__CONCEPT, oldConcept, concept));
			}
		}
		return concept;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Concept basicGetConcept() {
		return concept;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setConcept(Concept newConcept) {
		Concept oldConcept = concept;
		concept = newConcept;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LearningPackage.ERROR_PATTERN__CONCEPT, oldConcept, concept));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getSeverity() {
		return severity;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setSeverity(String newSeverity) {
		String oldSeverity = severity;
		severity = newSeverity;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LearningPackage.ERROR_PATTERN__SEVERITY, oldSeverity, severity));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getPedagogicalMeaning() {
		return pedagogicalMeaning;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setPedagogicalMeaning(String newPedagogicalMeaning) {
		String oldPedagogicalMeaning = pedagogicalMeaning;
		pedagogicalMeaning = newPedagogicalMeaning;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LearningPackage.ERROR_PATTERN__PEDAGOGICAL_MEANING, oldPedagogicalMeaning, pedagogicalMeaning));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case LearningPackage.ERROR_PATTERN__ID:
				return getId();
			case LearningPackage.ERROR_PATTERN__CONCEPT:
				if (resolve) return getConcept();
				return basicGetConcept();
			case LearningPackage.ERROR_PATTERN__SEVERITY:
				return getSeverity();
			case LearningPackage.ERROR_PATTERN__PEDAGOGICAL_MEANING:
				return getPedagogicalMeaning();
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
			case LearningPackage.ERROR_PATTERN__ID:
				setId((String)newValue);
				return;
			case LearningPackage.ERROR_PATTERN__CONCEPT:
				setConcept((Concept)newValue);
				return;
			case LearningPackage.ERROR_PATTERN__SEVERITY:
				setSeverity((String)newValue);
				return;
			case LearningPackage.ERROR_PATTERN__PEDAGOGICAL_MEANING:
				setPedagogicalMeaning((String)newValue);
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
			case LearningPackage.ERROR_PATTERN__ID:
				setId(ID_EDEFAULT);
				return;
			case LearningPackage.ERROR_PATTERN__CONCEPT:
				setConcept((Concept)null);
				return;
			case LearningPackage.ERROR_PATTERN__SEVERITY:
				setSeverity(SEVERITY_EDEFAULT);
				return;
			case LearningPackage.ERROR_PATTERN__PEDAGOGICAL_MEANING:
				setPedagogicalMeaning(PEDAGOGICAL_MEANING_EDEFAULT);
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
			case LearningPackage.ERROR_PATTERN__ID:
				return ID_EDEFAULT == null ? id != null : !ID_EDEFAULT.equals(id);
			case LearningPackage.ERROR_PATTERN__CONCEPT:
				return concept != null;
			case LearningPackage.ERROR_PATTERN__SEVERITY:
				return SEVERITY_EDEFAULT == null ? severity != null : !SEVERITY_EDEFAULT.equals(severity);
			case LearningPackage.ERROR_PATTERN__PEDAGOGICAL_MEANING:
				return PEDAGOGICAL_MEANING_EDEFAULT == null ? pedagogicalMeaning != null : !PEDAGOGICAL_MEANING_EDEFAULT.equals(pedagogicalMeaning);
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
		result.append(" (id: ");
		result.append(id);
		result.append(", severity: ");
		result.append(severity);
		result.append(", pedagogicalMeaning: ");
		result.append(pedagogicalMeaning);
		result.append(')');
		return result.toString();
	}

} //ErrorPatternImpl

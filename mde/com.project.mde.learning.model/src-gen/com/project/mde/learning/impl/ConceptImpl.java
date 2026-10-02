/**
 */
package com.project.mde.learning.impl;

import com.project.mde.learning.Activity;
import com.project.mde.learning.Concept;
import com.project.mde.learning.LearningPackage;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EObjectResolvingEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Concept</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link com.project.mde.learning.impl.ConceptImpl#getId <em>Id</em>}</li>
 *   <li>{@link com.project.mde.learning.impl.ConceptImpl#getName <em>Name</em>}</li>
 *   <li>{@link com.project.mde.learning.impl.ConceptImpl#getPrerequisites <em>Prerequisites</em>}</li>
 *   <li>{@link com.project.mde.learning.impl.ConceptImpl#getMinimumMasteryToUnlock <em>Minimum Mastery To Unlock</em>}</li>
 *   <li>{@link com.project.mde.learning.impl.ConceptImpl#getActivities <em>Activities</em>}</li>
 *   <li>{@link com.project.mde.learning.impl.ConceptImpl#getReinforcementActivities <em>Reinforcement Activities</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ConceptImpl extends MinimalEObjectImpl.Container implements Concept {
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
	 * The default value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected static final String NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected String name = NAME_EDEFAULT;

	/**
	 * The cached value of the '{@link #getPrerequisites() <em>Prerequisites</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPrerequisites()
	 * @generated
	 * @ordered
	 */
	protected EList<Concept> prerequisites;

	/**
	 * The default value of the '{@link #getMinimumMasteryToUnlock() <em>Minimum Mastery To Unlock</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMinimumMasteryToUnlock()
	 * @generated
	 * @ordered
	 */
	protected static final double MINIMUM_MASTERY_TO_UNLOCK_EDEFAULT = 0.0;

	/**
	 * The cached value of the '{@link #getMinimumMasteryToUnlock() <em>Minimum Mastery To Unlock</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMinimumMasteryToUnlock()
	 * @generated
	 * @ordered
	 */
	protected double minimumMasteryToUnlock = MINIMUM_MASTERY_TO_UNLOCK_EDEFAULT;

	/**
	 * The cached value of the '{@link #getActivities() <em>Activities</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getActivities()
	 * @generated
	 * @ordered
	 */
	protected EList<Activity> activities;

	/**
	 * The cached value of the '{@link #getReinforcementActivities() <em>Reinforcement Activities</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getReinforcementActivities()
	 * @generated
	 * @ordered
	 */
	protected EList<Activity> reinforcementActivities;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ConceptImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return LearningPackage.Literals.CONCEPT;
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
			eNotify(new ENotificationImpl(this, Notification.SET, LearningPackage.CONCEPT__ID, oldId, id));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getName() {
		return name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setName(String newName) {
		String oldName = name;
		name = newName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LearningPackage.CONCEPT__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<Concept> getPrerequisites() {
		if (prerequisites == null) {
			prerequisites = new EObjectResolvingEList<Concept>(Concept.class, this, LearningPackage.CONCEPT__PREREQUISITES);
		}
		return prerequisites;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public double getMinimumMasteryToUnlock() {
		return minimumMasteryToUnlock;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setMinimumMasteryToUnlock(double newMinimumMasteryToUnlock) {
		double oldMinimumMasteryToUnlock = minimumMasteryToUnlock;
		minimumMasteryToUnlock = newMinimumMasteryToUnlock;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LearningPackage.CONCEPT__MINIMUM_MASTERY_TO_UNLOCK, oldMinimumMasteryToUnlock, minimumMasteryToUnlock));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<Activity> getActivities() {
		if (activities == null) {
			activities = new EObjectResolvingEList<Activity>(Activity.class, this, LearningPackage.CONCEPT__ACTIVITIES);
		}
		return activities;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<Activity> getReinforcementActivities() {
		if (reinforcementActivities == null) {
			reinforcementActivities = new EObjectResolvingEList<Activity>(Activity.class, this, LearningPackage.CONCEPT__REINFORCEMENT_ACTIVITIES);
		}
		return reinforcementActivities;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case LearningPackage.CONCEPT__ID:
				return getId();
			case LearningPackage.CONCEPT__NAME:
				return getName();
			case LearningPackage.CONCEPT__PREREQUISITES:
				return getPrerequisites();
			case LearningPackage.CONCEPT__MINIMUM_MASTERY_TO_UNLOCK:
				return getMinimumMasteryToUnlock();
			case LearningPackage.CONCEPT__ACTIVITIES:
				return getActivities();
			case LearningPackage.CONCEPT__REINFORCEMENT_ACTIVITIES:
				return getReinforcementActivities();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case LearningPackage.CONCEPT__ID:
				setId((String)newValue);
				return;
			case LearningPackage.CONCEPT__NAME:
				setName((String)newValue);
				return;
			case LearningPackage.CONCEPT__PREREQUISITES:
				getPrerequisites().clear();
				getPrerequisites().addAll((Collection<? extends Concept>)newValue);
				return;
			case LearningPackage.CONCEPT__MINIMUM_MASTERY_TO_UNLOCK:
				setMinimumMasteryToUnlock((Double)newValue);
				return;
			case LearningPackage.CONCEPT__ACTIVITIES:
				getActivities().clear();
				getActivities().addAll((Collection<? extends Activity>)newValue);
				return;
			case LearningPackage.CONCEPT__REINFORCEMENT_ACTIVITIES:
				getReinforcementActivities().clear();
				getReinforcementActivities().addAll((Collection<? extends Activity>)newValue);
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
			case LearningPackage.CONCEPT__ID:
				setId(ID_EDEFAULT);
				return;
			case LearningPackage.CONCEPT__NAME:
				setName(NAME_EDEFAULT);
				return;
			case LearningPackage.CONCEPT__PREREQUISITES:
				getPrerequisites().clear();
				return;
			case LearningPackage.CONCEPT__MINIMUM_MASTERY_TO_UNLOCK:
				setMinimumMasteryToUnlock(MINIMUM_MASTERY_TO_UNLOCK_EDEFAULT);
				return;
			case LearningPackage.CONCEPT__ACTIVITIES:
				getActivities().clear();
				return;
			case LearningPackage.CONCEPT__REINFORCEMENT_ACTIVITIES:
				getReinforcementActivities().clear();
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
			case LearningPackage.CONCEPT__ID:
				return ID_EDEFAULT == null ? id != null : !ID_EDEFAULT.equals(id);
			case LearningPackage.CONCEPT__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case LearningPackage.CONCEPT__PREREQUISITES:
				return prerequisites != null && !prerequisites.isEmpty();
			case LearningPackage.CONCEPT__MINIMUM_MASTERY_TO_UNLOCK:
				return minimumMasteryToUnlock != MINIMUM_MASTERY_TO_UNLOCK_EDEFAULT;
			case LearningPackage.CONCEPT__ACTIVITIES:
				return activities != null && !activities.isEmpty();
			case LearningPackage.CONCEPT__REINFORCEMENT_ACTIVITIES:
				return reinforcementActivities != null && !reinforcementActivities.isEmpty();
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
		result.append(", name: ");
		result.append(name);
		result.append(", minimumMasteryToUnlock: ");
		result.append(minimumMasteryToUnlock);
		result.append(')');
		return result.toString();
	}

} //ConceptImpl

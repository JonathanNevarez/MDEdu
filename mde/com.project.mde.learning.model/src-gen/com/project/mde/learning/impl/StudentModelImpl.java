/**
 */
package com.project.mde.learning.impl;

import com.project.mde.learning.Activity;
import com.project.mde.learning.Attempt;
import com.project.mde.learning.Concept;
import com.project.mde.learning.ConceptMastery;
import com.project.mde.learning.ErrorPattern;
import com.project.mde.learning.HintUsage;
import com.project.mde.learning.LearningObjective;
import com.project.mde.learning.LearningPackage;
import com.project.mde.learning.Progress;
import com.project.mde.learning.Student;
import com.project.mde.learning.StudentModel;

import java.util.Collection;
import java.util.Date;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Student Model</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link com.project.mde.learning.impl.StudentModelImpl#getModelVersion <em>Model Version</em>}</li>
 *   <li>{@link com.project.mde.learning.impl.StudentModelImpl#getLastUpdated <em>Last Updated</em>}</li>
 *   <li>{@link com.project.mde.learning.impl.StudentModelImpl#getStudent <em>Student</em>}</li>
 *   <li>{@link com.project.mde.learning.impl.StudentModelImpl#getConcepts <em>Concepts</em>}</li>
 *   <li>{@link com.project.mde.learning.impl.StudentModelImpl#getActivities <em>Activities</em>}</li>
 *   <li>{@link com.project.mde.learning.impl.StudentModelImpl#getLearningObjectives <em>Learning Objectives</em>}</li>
 *   <li>{@link com.project.mde.learning.impl.StudentModelImpl#getErrorPatterns <em>Error Patterns</em>}</li>
 *   <li>{@link com.project.mde.learning.impl.StudentModelImpl#getConceptMasteries <em>Concept Masteries</em>}</li>
 *   <li>{@link com.project.mde.learning.impl.StudentModelImpl#getAttempts <em>Attempts</em>}</li>
 *   <li>{@link com.project.mde.learning.impl.StudentModelImpl#getHintUsages <em>Hint Usages</em>}</li>
 *   <li>{@link com.project.mde.learning.impl.StudentModelImpl#getProgress <em>Progress</em>}</li>
 * </ul>
 *
 * @generated
 */
public class StudentModelImpl extends MinimalEObjectImpl.Container implements StudentModel {
	/**
	 * The default value of the '{@link #getModelVersion() <em>Model Version</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getModelVersion()
	 * @generated
	 * @ordered
	 */
	protected static final int MODEL_VERSION_EDEFAULT = 1;

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
	 * The default value of the '{@link #getLastUpdated() <em>Last Updated</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLastUpdated()
	 * @generated
	 * @ordered
	 */
	protected static final Date LAST_UPDATED_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getLastUpdated() <em>Last Updated</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLastUpdated()
	 * @generated
	 * @ordered
	 */
	protected Date lastUpdated = LAST_UPDATED_EDEFAULT;

	/**
	 * The cached value of the '{@link #getStudent() <em>Student</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStudent()
	 * @generated
	 * @ordered
	 */
	protected Student student;

	/**
	 * The cached value of the '{@link #getConcepts() <em>Concepts</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getConcepts()
	 * @generated
	 * @ordered
	 */
	protected EList<Concept> concepts;

	/**
	 * The cached value of the '{@link #getActivities() <em>Activities</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getActivities()
	 * @generated
	 * @ordered
	 */
	protected EList<Activity> activities;

	/**
	 * The cached value of the '{@link #getLearningObjectives() <em>Learning Objectives</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLearningObjectives()
	 * @generated
	 * @ordered
	 */
	protected EList<LearningObjective> learningObjectives;

	/**
	 * The cached value of the '{@link #getErrorPatterns() <em>Error Patterns</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getErrorPatterns()
	 * @generated
	 * @ordered
	 */
	protected EList<ErrorPattern> errorPatterns;

	/**
	 * The cached value of the '{@link #getConceptMasteries() <em>Concept Masteries</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getConceptMasteries()
	 * @generated
	 * @ordered
	 */
	protected EList<ConceptMastery> conceptMasteries;

	/**
	 * The cached value of the '{@link #getAttempts() <em>Attempts</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAttempts()
	 * @generated
	 * @ordered
	 */
	protected EList<Attempt> attempts;

	/**
	 * The cached value of the '{@link #getHintUsages() <em>Hint Usages</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getHintUsages()
	 * @generated
	 * @ordered
	 */
	protected EList<HintUsage> hintUsages;

	/**
	 * The cached value of the '{@link #getProgress() <em>Progress</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getProgress()
	 * @generated
	 * @ordered
	 */
	protected EList<Progress> progress;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected StudentModelImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return LearningPackage.Literals.STUDENT_MODEL;
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
			eNotify(new ENotificationImpl(this, Notification.SET, LearningPackage.STUDENT_MODEL__MODEL_VERSION, oldModelVersion, modelVersion));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Date getLastUpdated() {
		return lastUpdated;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setLastUpdated(Date newLastUpdated) {
		Date oldLastUpdated = lastUpdated;
		lastUpdated = newLastUpdated;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LearningPackage.STUDENT_MODEL__LAST_UPDATED, oldLastUpdated, lastUpdated));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Student getStudent() {
		return student;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetStudent(Student newStudent, NotificationChain msgs) {
		Student oldStudent = student;
		student = newStudent;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, LearningPackage.STUDENT_MODEL__STUDENT, oldStudent, newStudent);
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
	public void setStudent(Student newStudent) {
		if (newStudent != student) {
			NotificationChain msgs = null;
			if (student != null)
				msgs = ((InternalEObject)student).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - LearningPackage.STUDENT_MODEL__STUDENT, null, msgs);
			if (newStudent != null)
				msgs = ((InternalEObject)newStudent).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - LearningPackage.STUDENT_MODEL__STUDENT, null, msgs);
			msgs = basicSetStudent(newStudent, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LearningPackage.STUDENT_MODEL__STUDENT, newStudent, newStudent));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<Concept> getConcepts() {
		if (concepts == null) {
			concepts = new EObjectContainmentEList<Concept>(Concept.class, this, LearningPackage.STUDENT_MODEL__CONCEPTS);
		}
		return concepts;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<Activity> getActivities() {
		if (activities == null) {
			activities = new EObjectContainmentEList<Activity>(Activity.class, this, LearningPackage.STUDENT_MODEL__ACTIVITIES);
		}
		return activities;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<LearningObjective> getLearningObjectives() {
		if (learningObjectives == null) {
			learningObjectives = new EObjectContainmentEList<LearningObjective>(LearningObjective.class, this, LearningPackage.STUDENT_MODEL__LEARNING_OBJECTIVES);
		}
		return learningObjectives;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<ErrorPattern> getErrorPatterns() {
		if (errorPatterns == null) {
			errorPatterns = new EObjectContainmentEList<ErrorPattern>(ErrorPattern.class, this, LearningPackage.STUDENT_MODEL__ERROR_PATTERNS);
		}
		return errorPatterns;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<ConceptMastery> getConceptMasteries() {
		if (conceptMasteries == null) {
			conceptMasteries = new EObjectContainmentEList<ConceptMastery>(ConceptMastery.class, this, LearningPackage.STUDENT_MODEL__CONCEPT_MASTERIES);
		}
		return conceptMasteries;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<Attempt> getAttempts() {
		if (attempts == null) {
			attempts = new EObjectContainmentEList<Attempt>(Attempt.class, this, LearningPackage.STUDENT_MODEL__ATTEMPTS);
		}
		return attempts;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<HintUsage> getHintUsages() {
		if (hintUsages == null) {
			hintUsages = new EObjectContainmentEList<HintUsage>(HintUsage.class, this, LearningPackage.STUDENT_MODEL__HINT_USAGES);
		}
		return hintUsages;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<Progress> getProgress() {
		if (progress == null) {
			progress = new EObjectContainmentEList<Progress>(Progress.class, this, LearningPackage.STUDENT_MODEL__PROGRESS);
		}
		return progress;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case LearningPackage.STUDENT_MODEL__STUDENT:
				return basicSetStudent(null, msgs);
			case LearningPackage.STUDENT_MODEL__CONCEPTS:
				return ((InternalEList<?>)getConcepts()).basicRemove(otherEnd, msgs);
			case LearningPackage.STUDENT_MODEL__ACTIVITIES:
				return ((InternalEList<?>)getActivities()).basicRemove(otherEnd, msgs);
			case LearningPackage.STUDENT_MODEL__LEARNING_OBJECTIVES:
				return ((InternalEList<?>)getLearningObjectives()).basicRemove(otherEnd, msgs);
			case LearningPackage.STUDENT_MODEL__ERROR_PATTERNS:
				return ((InternalEList<?>)getErrorPatterns()).basicRemove(otherEnd, msgs);
			case LearningPackage.STUDENT_MODEL__CONCEPT_MASTERIES:
				return ((InternalEList<?>)getConceptMasteries()).basicRemove(otherEnd, msgs);
			case LearningPackage.STUDENT_MODEL__ATTEMPTS:
				return ((InternalEList<?>)getAttempts()).basicRemove(otherEnd, msgs);
			case LearningPackage.STUDENT_MODEL__HINT_USAGES:
				return ((InternalEList<?>)getHintUsages()).basicRemove(otherEnd, msgs);
			case LearningPackage.STUDENT_MODEL__PROGRESS:
				return ((InternalEList<?>)getProgress()).basicRemove(otherEnd, msgs);
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
			case LearningPackage.STUDENT_MODEL__MODEL_VERSION:
				return getModelVersion();
			case LearningPackage.STUDENT_MODEL__LAST_UPDATED:
				return getLastUpdated();
			case LearningPackage.STUDENT_MODEL__STUDENT:
				return getStudent();
			case LearningPackage.STUDENT_MODEL__CONCEPTS:
				return getConcepts();
			case LearningPackage.STUDENT_MODEL__ACTIVITIES:
				return getActivities();
			case LearningPackage.STUDENT_MODEL__LEARNING_OBJECTIVES:
				return getLearningObjectives();
			case LearningPackage.STUDENT_MODEL__ERROR_PATTERNS:
				return getErrorPatterns();
			case LearningPackage.STUDENT_MODEL__CONCEPT_MASTERIES:
				return getConceptMasteries();
			case LearningPackage.STUDENT_MODEL__ATTEMPTS:
				return getAttempts();
			case LearningPackage.STUDENT_MODEL__HINT_USAGES:
				return getHintUsages();
			case LearningPackage.STUDENT_MODEL__PROGRESS:
				return getProgress();
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
			case LearningPackage.STUDENT_MODEL__MODEL_VERSION:
				setModelVersion((Integer)newValue);
				return;
			case LearningPackage.STUDENT_MODEL__LAST_UPDATED:
				setLastUpdated((Date)newValue);
				return;
			case LearningPackage.STUDENT_MODEL__STUDENT:
				setStudent((Student)newValue);
				return;
			case LearningPackage.STUDENT_MODEL__CONCEPTS:
				getConcepts().clear();
				getConcepts().addAll((Collection<? extends Concept>)newValue);
				return;
			case LearningPackage.STUDENT_MODEL__ACTIVITIES:
				getActivities().clear();
				getActivities().addAll((Collection<? extends Activity>)newValue);
				return;
			case LearningPackage.STUDENT_MODEL__LEARNING_OBJECTIVES:
				getLearningObjectives().clear();
				getLearningObjectives().addAll((Collection<? extends LearningObjective>)newValue);
				return;
			case LearningPackage.STUDENT_MODEL__ERROR_PATTERNS:
				getErrorPatterns().clear();
				getErrorPatterns().addAll((Collection<? extends ErrorPattern>)newValue);
				return;
			case LearningPackage.STUDENT_MODEL__CONCEPT_MASTERIES:
				getConceptMasteries().clear();
				getConceptMasteries().addAll((Collection<? extends ConceptMastery>)newValue);
				return;
			case LearningPackage.STUDENT_MODEL__ATTEMPTS:
				getAttempts().clear();
				getAttempts().addAll((Collection<? extends Attempt>)newValue);
				return;
			case LearningPackage.STUDENT_MODEL__HINT_USAGES:
				getHintUsages().clear();
				getHintUsages().addAll((Collection<? extends HintUsage>)newValue);
				return;
			case LearningPackage.STUDENT_MODEL__PROGRESS:
				getProgress().clear();
				getProgress().addAll((Collection<? extends Progress>)newValue);
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
			case LearningPackage.STUDENT_MODEL__MODEL_VERSION:
				setModelVersion(MODEL_VERSION_EDEFAULT);
				return;
			case LearningPackage.STUDENT_MODEL__LAST_UPDATED:
				setLastUpdated(LAST_UPDATED_EDEFAULT);
				return;
			case LearningPackage.STUDENT_MODEL__STUDENT:
				setStudent((Student)null);
				return;
			case LearningPackage.STUDENT_MODEL__CONCEPTS:
				getConcepts().clear();
				return;
			case LearningPackage.STUDENT_MODEL__ACTIVITIES:
				getActivities().clear();
				return;
			case LearningPackage.STUDENT_MODEL__LEARNING_OBJECTIVES:
				getLearningObjectives().clear();
				return;
			case LearningPackage.STUDENT_MODEL__ERROR_PATTERNS:
				getErrorPatterns().clear();
				return;
			case LearningPackage.STUDENT_MODEL__CONCEPT_MASTERIES:
				getConceptMasteries().clear();
				return;
			case LearningPackage.STUDENT_MODEL__ATTEMPTS:
				getAttempts().clear();
				return;
			case LearningPackage.STUDENT_MODEL__HINT_USAGES:
				getHintUsages().clear();
				return;
			case LearningPackage.STUDENT_MODEL__PROGRESS:
				getProgress().clear();
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
			case LearningPackage.STUDENT_MODEL__MODEL_VERSION:
				return modelVersion != MODEL_VERSION_EDEFAULT;
			case LearningPackage.STUDENT_MODEL__LAST_UPDATED:
				return LAST_UPDATED_EDEFAULT == null ? lastUpdated != null : !LAST_UPDATED_EDEFAULT.equals(lastUpdated);
			case LearningPackage.STUDENT_MODEL__STUDENT:
				return student != null;
			case LearningPackage.STUDENT_MODEL__CONCEPTS:
				return concepts != null && !concepts.isEmpty();
			case LearningPackage.STUDENT_MODEL__ACTIVITIES:
				return activities != null && !activities.isEmpty();
			case LearningPackage.STUDENT_MODEL__LEARNING_OBJECTIVES:
				return learningObjectives != null && !learningObjectives.isEmpty();
			case LearningPackage.STUDENT_MODEL__ERROR_PATTERNS:
				return errorPatterns != null && !errorPatterns.isEmpty();
			case LearningPackage.STUDENT_MODEL__CONCEPT_MASTERIES:
				return conceptMasteries != null && !conceptMasteries.isEmpty();
			case LearningPackage.STUDENT_MODEL__ATTEMPTS:
				return attempts != null && !attempts.isEmpty();
			case LearningPackage.STUDENT_MODEL__HINT_USAGES:
				return hintUsages != null && !hintUsages.isEmpty();
			case LearningPackage.STUDENT_MODEL__PROGRESS:
				return progress != null && !progress.isEmpty();
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
		result.append(", lastUpdated: ");
		result.append(lastUpdated);
		result.append(')');
		return result.toString();
	}

} //StudentModelImpl

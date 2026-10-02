/**
 */
package com.project.mde.learning;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each operation of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see com.project.mde.learning.LearningFactory
 * @model kind="package"
 * @generated
 */
public interface LearningPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "learning";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "https://mdedu.espoch.edu.ec/model/learning/1.0";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "learning";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	LearningPackage eINSTANCE = com.project.mde.learning.impl.LearningPackageImpl.init();

	/**
	 * The meta object id for the '{@link com.project.mde.learning.impl.StudentImpl <em>Student</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.project.mde.learning.impl.StudentImpl
	 * @see com.project.mde.learning.impl.LearningPackageImpl#getStudent()
	 * @generated
	 */
	int STUDENT = 0;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STUDENT__ID = 0;

	/**
	 * The feature id for the '<em><b>Display Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STUDENT__DISPLAY_NAME = 1;

	/**
	 * The feature id for the '<em><b>Created At</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STUDENT__CREATED_AT = 2;

	/**
	 * The number of structural features of the '<em>Student</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STUDENT_FEATURE_COUNT = 3;

	/**
	 * The number of operations of the '<em>Student</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STUDENT_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link com.project.mde.learning.impl.StudentModelImpl <em>Student Model</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.project.mde.learning.impl.StudentModelImpl
	 * @see com.project.mde.learning.impl.LearningPackageImpl#getStudentModel()
	 * @generated
	 */
	int STUDENT_MODEL = 1;

	/**
	 * The feature id for the '<em><b>Model Version</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STUDENT_MODEL__MODEL_VERSION = 0;

	/**
	 * The feature id for the '<em><b>Last Updated</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STUDENT_MODEL__LAST_UPDATED = 1;

	/**
	 * The feature id for the '<em><b>Student</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STUDENT_MODEL__STUDENT = 2;

	/**
	 * The feature id for the '<em><b>Concepts</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STUDENT_MODEL__CONCEPTS = 3;

	/**
	 * The feature id for the '<em><b>Activities</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STUDENT_MODEL__ACTIVITIES = 4;

	/**
	 * The feature id for the '<em><b>Learning Objectives</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STUDENT_MODEL__LEARNING_OBJECTIVES = 5;

	/**
	 * The feature id for the '<em><b>Error Patterns</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STUDENT_MODEL__ERROR_PATTERNS = 6;

	/**
	 * The feature id for the '<em><b>Concept Masteries</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STUDENT_MODEL__CONCEPT_MASTERIES = 7;

	/**
	 * The feature id for the '<em><b>Attempts</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STUDENT_MODEL__ATTEMPTS = 8;

	/**
	 * The feature id for the '<em><b>Hint Usages</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STUDENT_MODEL__HINT_USAGES = 9;

	/**
	 * The feature id for the '<em><b>Progress</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STUDENT_MODEL__PROGRESS = 10;

	/**
	 * The number of structural features of the '<em>Student Model</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STUDENT_MODEL_FEATURE_COUNT = 11;

	/**
	 * The number of operations of the '<em>Student Model</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STUDENT_MODEL_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link com.project.mde.learning.impl.ConceptImpl <em>Concept</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.project.mde.learning.impl.ConceptImpl
	 * @see com.project.mde.learning.impl.LearningPackageImpl#getConcept()
	 * @generated
	 */
	int CONCEPT = 2;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONCEPT__ID = 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONCEPT__NAME = 1;

	/**
	 * The feature id for the '<em><b>Prerequisites</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONCEPT__PREREQUISITES = 2;

	/**
	 * The feature id for the '<em><b>Minimum Mastery To Unlock</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONCEPT__MINIMUM_MASTERY_TO_UNLOCK = 3;

	/**
	 * The feature id for the '<em><b>Activities</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONCEPT__ACTIVITIES = 4;

	/**
	 * The feature id for the '<em><b>Reinforcement Activities</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONCEPT__REINFORCEMENT_ACTIVITIES = 5;

	/**
	 * The number of structural features of the '<em>Concept</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONCEPT_FEATURE_COUNT = 6;

	/**
	 * The number of operations of the '<em>Concept</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONCEPT_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link com.project.mde.learning.impl.ConceptMasteryImpl <em>Concept Mastery</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.project.mde.learning.impl.ConceptMasteryImpl
	 * @see com.project.mde.learning.impl.LearningPackageImpl#getConceptMastery()
	 * @generated
	 */
	int CONCEPT_MASTERY = 3;

	/**
	 * The feature id for the '<em><b>Student</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONCEPT_MASTERY__STUDENT = 0;

	/**
	 * The feature id for the '<em><b>Concept</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONCEPT_MASTERY__CONCEPT = 1;

	/**
	 * The feature id for the '<em><b>Mastery Score</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONCEPT_MASTERY__MASTERY_SCORE = 2;

	/**
	 * The feature id for the '<em><b>Attempt Count</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONCEPT_MASTERY__ATTEMPT_COUNT = 3;

	/**
	 * The feature id for the '<em><b>Success Count</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONCEPT_MASTERY__SUCCESS_COUNT = 4;

	/**
	 * The feature id for the '<em><b>Failure Count</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONCEPT_MASTERY__FAILURE_COUNT = 5;

	/**
	 * The feature id for the '<em><b>Consecutive Failures</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONCEPT_MASTERY__CONSECUTIVE_FAILURES = 6;

	/**
	 * The feature id for the '<em><b>Hint Count</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONCEPT_MASTERY__HINT_COUNT = 7;

	/**
	 * The feature id for the '<em><b>Average Resolution Time</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONCEPT_MASTERY__AVERAGE_RESOLUTION_TIME = 8;

	/**
	 * The feature id for the '<em><b>Last Updated</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONCEPT_MASTERY__LAST_UPDATED = 9;

	/**
	 * The feature id for the '<em><b>Recent Error Patterns</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONCEPT_MASTERY__RECENT_ERROR_PATTERNS = 10;

	/**
	 * The number of structural features of the '<em>Concept Mastery</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONCEPT_MASTERY_FEATURE_COUNT = 11;

	/**
	 * The number of operations of the '<em>Concept Mastery</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONCEPT_MASTERY_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link com.project.mde.learning.impl.LearningObjectiveImpl <em>Objective</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.project.mde.learning.impl.LearningObjectiveImpl
	 * @see com.project.mde.learning.impl.LearningPackageImpl#getLearningObjective()
	 * @generated
	 */
	int LEARNING_OBJECTIVE = 4;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LEARNING_OBJECTIVE__ID = 0;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LEARNING_OBJECTIVE__DESCRIPTION = 1;

	/**
	 * The feature id for the '<em><b>Concept</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LEARNING_OBJECTIVE__CONCEPT = 2;

	/**
	 * The number of structural features of the '<em>Objective</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LEARNING_OBJECTIVE_FEATURE_COUNT = 3;

	/**
	 * The number of operations of the '<em>Objective</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LEARNING_OBJECTIVE_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link com.project.mde.learning.impl.ActivityImpl <em>Activity</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.project.mde.learning.impl.ActivityImpl
	 * @see com.project.mde.learning.impl.LearningPackageImpl#getActivity()
	 * @generated
	 */
	int ACTIVITY = 5;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY__ID = 0;

	/**
	 * The feature id for the '<em><b>Title</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY__TITLE = 1;

	/**
	 * The feature id for the '<em><b>Concept</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY__CONCEPT = 2;

	/**
	 * The feature id for the '<em><b>Learning Objectives</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY__LEARNING_OBJECTIVES = 3;

	/**
	 * The number of structural features of the '<em>Activity</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY_FEATURE_COUNT = 4;

	/**
	 * The number of operations of the '<em>Activity</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link com.project.mde.learning.impl.AttemptImpl <em>Attempt</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.project.mde.learning.impl.AttemptImpl
	 * @see com.project.mde.learning.impl.LearningPackageImpl#getAttempt()
	 * @generated
	 */
	int ATTEMPT = 6;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATTEMPT__ID = 0;

	/**
	 * The feature id for the '<em><b>Student</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATTEMPT__STUDENT = 1;

	/**
	 * The feature id for the '<em><b>Activity</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATTEMPT__ACTIVITY = 2;

	/**
	 * The feature id for the '<em><b>Concept</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATTEMPT__CONCEPT = 3;

	/**
	 * The feature id for the '<em><b>Successful</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATTEMPT__SUCCESSFUL = 4;

	/**
	 * The feature id for the '<em><b>Functional Passed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATTEMPT__FUNCTIONAL_PASSED = 5;

	/**
	 * The feature id for the '<em><b>Resolution Time</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATTEMPT__RESOLUTION_TIME = 6;

	/**
	 * The feature id for the '<em><b>Hint Count</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATTEMPT__HINT_COUNT = 7;

	/**
	 * The feature id for the '<em><b>Submitted At</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATTEMPT__SUBMITTED_AT = 8;

	/**
	 * The feature id for the '<em><b>Error Patterns</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATTEMPT__ERROR_PATTERNS = 9;

	/**
	 * The number of structural features of the '<em>Attempt</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATTEMPT_FEATURE_COUNT = 10;

	/**
	 * The number of operations of the '<em>Attempt</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATTEMPT_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link com.project.mde.learning.impl.ErrorPatternImpl <em>Error Pattern</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.project.mde.learning.impl.ErrorPatternImpl
	 * @see com.project.mde.learning.impl.LearningPackageImpl#getErrorPattern()
	 * @generated
	 */
	int ERROR_PATTERN = 7;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ERROR_PATTERN__ID = 0;

	/**
	 * The feature id for the '<em><b>Concept</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ERROR_PATTERN__CONCEPT = 1;

	/**
	 * The feature id for the '<em><b>Severity</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ERROR_PATTERN__SEVERITY = 2;

	/**
	 * The feature id for the '<em><b>Pedagogical Meaning</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ERROR_PATTERN__PEDAGOGICAL_MEANING = 3;

	/**
	 * The number of structural features of the '<em>Error Pattern</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ERROR_PATTERN_FEATURE_COUNT = 4;

	/**
	 * The number of operations of the '<em>Error Pattern</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ERROR_PATTERN_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link com.project.mde.learning.impl.HintUsageImpl <em>Hint Usage</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.project.mde.learning.impl.HintUsageImpl
	 * @see com.project.mde.learning.impl.LearningPackageImpl#getHintUsage()
	 * @generated
	 */
	int HINT_USAGE = 8;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HINT_USAGE__ID = 0;

	/**
	 * The feature id for the '<em><b>Student</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HINT_USAGE__STUDENT = 1;

	/**
	 * The feature id for the '<em><b>Activity</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HINT_USAGE__ACTIVITY = 2;

	/**
	 * The feature id for the '<em><b>Hint Level</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HINT_USAGE__HINT_LEVEL = 3;

	/**
	 * The feature id for the '<em><b>Used At</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HINT_USAGE__USED_AT = 4;

	/**
	 * The number of structural features of the '<em>Hint Usage</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HINT_USAGE_FEATURE_COUNT = 5;

	/**
	 * The number of operations of the '<em>Hint Usage</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HINT_USAGE_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link com.project.mde.learning.impl.ProgressImpl <em>Progress</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.project.mde.learning.impl.ProgressImpl
	 * @see com.project.mde.learning.impl.LearningPackageImpl#getProgress()
	 * @generated
	 */
	int PROGRESS = 9;

	/**
	 * The feature id for the '<em><b>Activity</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROGRESS__ACTIVITY = 0;

	/**
	 * The feature id for the '<em><b>Concept</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROGRESS__CONCEPT = 1;

	/**
	 * The feature id for the '<em><b>Completed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROGRESS__COMPLETED = 2;

	/**
	 * The feature id for the '<em><b>Unlocked</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROGRESS__UNLOCKED = 3;

	/**
	 * The feature id for the '<em><b>Completed At</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROGRESS__COMPLETED_AT = 4;

	/**
	 * The number of structural features of the '<em>Progress</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROGRESS_FEATURE_COUNT = 5;

	/**
	 * The number of operations of the '<em>Progress</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROGRESS_OPERATION_COUNT = 0;


	/**
	 * Returns the meta object for class '{@link com.project.mde.learning.Student <em>Student</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Student</em>'.
	 * @see com.project.mde.learning.Student
	 * @generated
	 */
	EClass getStudent();

	/**
	 * Returns the meta object for the attribute '{@link com.project.mde.learning.Student#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see com.project.mde.learning.Student#getId()
	 * @see #getStudent()
	 * @generated
	 */
	EAttribute getStudent_Id();

	/**
	 * Returns the meta object for the attribute '{@link com.project.mde.learning.Student#getDisplayName <em>Display Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Display Name</em>'.
	 * @see com.project.mde.learning.Student#getDisplayName()
	 * @see #getStudent()
	 * @generated
	 */
	EAttribute getStudent_DisplayName();

	/**
	 * Returns the meta object for the attribute '{@link com.project.mde.learning.Student#getCreatedAt <em>Created At</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Created At</em>'.
	 * @see com.project.mde.learning.Student#getCreatedAt()
	 * @see #getStudent()
	 * @generated
	 */
	EAttribute getStudent_CreatedAt();

	/**
	 * Returns the meta object for class '{@link com.project.mde.learning.StudentModel <em>Student Model</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Student Model</em>'.
	 * @see com.project.mde.learning.StudentModel
	 * @generated
	 */
	EClass getStudentModel();

	/**
	 * Returns the meta object for the attribute '{@link com.project.mde.learning.StudentModel#getModelVersion <em>Model Version</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Model Version</em>'.
	 * @see com.project.mde.learning.StudentModel#getModelVersion()
	 * @see #getStudentModel()
	 * @generated
	 */
	EAttribute getStudentModel_ModelVersion();

	/**
	 * Returns the meta object for the attribute '{@link com.project.mde.learning.StudentModel#getLastUpdated <em>Last Updated</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Last Updated</em>'.
	 * @see com.project.mde.learning.StudentModel#getLastUpdated()
	 * @see #getStudentModel()
	 * @generated
	 */
	EAttribute getStudentModel_LastUpdated();

	/**
	 * Returns the meta object for the containment reference '{@link com.project.mde.learning.StudentModel#getStudent <em>Student</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Student</em>'.
	 * @see com.project.mde.learning.StudentModel#getStudent()
	 * @see #getStudentModel()
	 * @generated
	 */
	EReference getStudentModel_Student();

	/**
	 * Returns the meta object for the containment reference list '{@link com.project.mde.learning.StudentModel#getConcepts <em>Concepts</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Concepts</em>'.
	 * @see com.project.mde.learning.StudentModel#getConcepts()
	 * @see #getStudentModel()
	 * @generated
	 */
	EReference getStudentModel_Concepts();

	/**
	 * Returns the meta object for the containment reference list '{@link com.project.mde.learning.StudentModel#getActivities <em>Activities</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Activities</em>'.
	 * @see com.project.mde.learning.StudentModel#getActivities()
	 * @see #getStudentModel()
	 * @generated
	 */
	EReference getStudentModel_Activities();

	/**
	 * Returns the meta object for the containment reference list '{@link com.project.mde.learning.StudentModel#getLearningObjectives <em>Learning Objectives</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Learning Objectives</em>'.
	 * @see com.project.mde.learning.StudentModel#getLearningObjectives()
	 * @see #getStudentModel()
	 * @generated
	 */
	EReference getStudentModel_LearningObjectives();

	/**
	 * Returns the meta object for the containment reference list '{@link com.project.mde.learning.StudentModel#getErrorPatterns <em>Error Patterns</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Error Patterns</em>'.
	 * @see com.project.mde.learning.StudentModel#getErrorPatterns()
	 * @see #getStudentModel()
	 * @generated
	 */
	EReference getStudentModel_ErrorPatterns();

	/**
	 * Returns the meta object for the containment reference list '{@link com.project.mde.learning.StudentModel#getConceptMasteries <em>Concept Masteries</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Concept Masteries</em>'.
	 * @see com.project.mde.learning.StudentModel#getConceptMasteries()
	 * @see #getStudentModel()
	 * @generated
	 */
	EReference getStudentModel_ConceptMasteries();

	/**
	 * Returns the meta object for the containment reference list '{@link com.project.mde.learning.StudentModel#getAttempts <em>Attempts</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Attempts</em>'.
	 * @see com.project.mde.learning.StudentModel#getAttempts()
	 * @see #getStudentModel()
	 * @generated
	 */
	EReference getStudentModel_Attempts();

	/**
	 * Returns the meta object for the containment reference list '{@link com.project.mde.learning.StudentModel#getHintUsages <em>Hint Usages</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Hint Usages</em>'.
	 * @see com.project.mde.learning.StudentModel#getHintUsages()
	 * @see #getStudentModel()
	 * @generated
	 */
	EReference getStudentModel_HintUsages();

	/**
	 * Returns the meta object for the containment reference list '{@link com.project.mde.learning.StudentModel#getProgress <em>Progress</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Progress</em>'.
	 * @see com.project.mde.learning.StudentModel#getProgress()
	 * @see #getStudentModel()
	 * @generated
	 */
	EReference getStudentModel_Progress();

	/**
	 * Returns the meta object for class '{@link com.project.mde.learning.Concept <em>Concept</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Concept</em>'.
	 * @see com.project.mde.learning.Concept
	 * @generated
	 */
	EClass getConcept();

	/**
	 * Returns the meta object for the attribute '{@link com.project.mde.learning.Concept#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see com.project.mde.learning.Concept#getId()
	 * @see #getConcept()
	 * @generated
	 */
	EAttribute getConcept_Id();

	/**
	 * Returns the meta object for the attribute '{@link com.project.mde.learning.Concept#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see com.project.mde.learning.Concept#getName()
	 * @see #getConcept()
	 * @generated
	 */
	EAttribute getConcept_Name();

	/**
	 * Returns the meta object for the reference list '{@link com.project.mde.learning.Concept#getPrerequisites <em>Prerequisites</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Prerequisites</em>'.
	 * @see com.project.mde.learning.Concept#getPrerequisites()
	 * @see #getConcept()
	 * @generated
	 */
	EReference getConcept_Prerequisites();

	/**
	 * Returns the meta object for the attribute '{@link com.project.mde.learning.Concept#getMinimumMasteryToUnlock <em>Minimum Mastery To Unlock</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Minimum Mastery To Unlock</em>'.
	 * @see com.project.mde.learning.Concept#getMinimumMasteryToUnlock()
	 * @see #getConcept()
	 * @generated
	 */
	EAttribute getConcept_MinimumMasteryToUnlock();

	/**
	 * Returns the meta object for the reference list '{@link com.project.mde.learning.Concept#getActivities <em>Activities</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Activities</em>'.
	 * @see com.project.mde.learning.Concept#getActivities()
	 * @see #getConcept()
	 * @generated
	 */
	EReference getConcept_Activities();

	/**
	 * Returns the meta object for the reference list '{@link com.project.mde.learning.Concept#getReinforcementActivities <em>Reinforcement Activities</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Reinforcement Activities</em>'.
	 * @see com.project.mde.learning.Concept#getReinforcementActivities()
	 * @see #getConcept()
	 * @generated
	 */
	EReference getConcept_ReinforcementActivities();

	/**
	 * Returns the meta object for class '{@link com.project.mde.learning.ConceptMastery <em>Concept Mastery</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Concept Mastery</em>'.
	 * @see com.project.mde.learning.ConceptMastery
	 * @generated
	 */
	EClass getConceptMastery();

	/**
	 * Returns the meta object for the reference '{@link com.project.mde.learning.ConceptMastery#getStudent <em>Student</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Student</em>'.
	 * @see com.project.mde.learning.ConceptMastery#getStudent()
	 * @see #getConceptMastery()
	 * @generated
	 */
	EReference getConceptMastery_Student();

	/**
	 * Returns the meta object for the reference '{@link com.project.mde.learning.ConceptMastery#getConcept <em>Concept</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Concept</em>'.
	 * @see com.project.mde.learning.ConceptMastery#getConcept()
	 * @see #getConceptMastery()
	 * @generated
	 */
	EReference getConceptMastery_Concept();

	/**
	 * Returns the meta object for the attribute '{@link com.project.mde.learning.ConceptMastery#getMasteryScore <em>Mastery Score</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Mastery Score</em>'.
	 * @see com.project.mde.learning.ConceptMastery#getMasteryScore()
	 * @see #getConceptMastery()
	 * @generated
	 */
	EAttribute getConceptMastery_MasteryScore();

	/**
	 * Returns the meta object for the attribute '{@link com.project.mde.learning.ConceptMastery#getAttemptCount <em>Attempt Count</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Attempt Count</em>'.
	 * @see com.project.mde.learning.ConceptMastery#getAttemptCount()
	 * @see #getConceptMastery()
	 * @generated
	 */
	EAttribute getConceptMastery_AttemptCount();

	/**
	 * Returns the meta object for the attribute '{@link com.project.mde.learning.ConceptMastery#getSuccessCount <em>Success Count</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Success Count</em>'.
	 * @see com.project.mde.learning.ConceptMastery#getSuccessCount()
	 * @see #getConceptMastery()
	 * @generated
	 */
	EAttribute getConceptMastery_SuccessCount();

	/**
	 * Returns the meta object for the attribute '{@link com.project.mde.learning.ConceptMastery#getFailureCount <em>Failure Count</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Failure Count</em>'.
	 * @see com.project.mde.learning.ConceptMastery#getFailureCount()
	 * @see #getConceptMastery()
	 * @generated
	 */
	EAttribute getConceptMastery_FailureCount();

	/**
	 * Returns the meta object for the attribute '{@link com.project.mde.learning.ConceptMastery#getConsecutiveFailures <em>Consecutive Failures</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Consecutive Failures</em>'.
	 * @see com.project.mde.learning.ConceptMastery#getConsecutiveFailures()
	 * @see #getConceptMastery()
	 * @generated
	 */
	EAttribute getConceptMastery_ConsecutiveFailures();

	/**
	 * Returns the meta object for the attribute '{@link com.project.mde.learning.ConceptMastery#getHintCount <em>Hint Count</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Hint Count</em>'.
	 * @see com.project.mde.learning.ConceptMastery#getHintCount()
	 * @see #getConceptMastery()
	 * @generated
	 */
	EAttribute getConceptMastery_HintCount();

	/**
	 * Returns the meta object for the attribute '{@link com.project.mde.learning.ConceptMastery#getAverageResolutionTime <em>Average Resolution Time</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Average Resolution Time</em>'.
	 * @see com.project.mde.learning.ConceptMastery#getAverageResolutionTime()
	 * @see #getConceptMastery()
	 * @generated
	 */
	EAttribute getConceptMastery_AverageResolutionTime();

	/**
	 * Returns the meta object for the attribute '{@link com.project.mde.learning.ConceptMastery#getLastUpdated <em>Last Updated</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Last Updated</em>'.
	 * @see com.project.mde.learning.ConceptMastery#getLastUpdated()
	 * @see #getConceptMastery()
	 * @generated
	 */
	EAttribute getConceptMastery_LastUpdated();

	/**
	 * Returns the meta object for the reference list '{@link com.project.mde.learning.ConceptMastery#getRecentErrorPatterns <em>Recent Error Patterns</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Recent Error Patterns</em>'.
	 * @see com.project.mde.learning.ConceptMastery#getRecentErrorPatterns()
	 * @see #getConceptMastery()
	 * @generated
	 */
	EReference getConceptMastery_RecentErrorPatterns();

	/**
	 * Returns the meta object for class '{@link com.project.mde.learning.LearningObjective <em>Objective</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Objective</em>'.
	 * @see com.project.mde.learning.LearningObjective
	 * @generated
	 */
	EClass getLearningObjective();

	/**
	 * Returns the meta object for the attribute '{@link com.project.mde.learning.LearningObjective#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see com.project.mde.learning.LearningObjective#getId()
	 * @see #getLearningObjective()
	 * @generated
	 */
	EAttribute getLearningObjective_Id();

	/**
	 * Returns the meta object for the attribute '{@link com.project.mde.learning.LearningObjective#getDescription <em>Description</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Description</em>'.
	 * @see com.project.mde.learning.LearningObjective#getDescription()
	 * @see #getLearningObjective()
	 * @generated
	 */
	EAttribute getLearningObjective_Description();

	/**
	 * Returns the meta object for the reference '{@link com.project.mde.learning.LearningObjective#getConcept <em>Concept</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Concept</em>'.
	 * @see com.project.mde.learning.LearningObjective#getConcept()
	 * @see #getLearningObjective()
	 * @generated
	 */
	EReference getLearningObjective_Concept();

	/**
	 * Returns the meta object for class '{@link com.project.mde.learning.Activity <em>Activity</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Activity</em>'.
	 * @see com.project.mde.learning.Activity
	 * @generated
	 */
	EClass getActivity();

	/**
	 * Returns the meta object for the attribute '{@link com.project.mde.learning.Activity#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see com.project.mde.learning.Activity#getId()
	 * @see #getActivity()
	 * @generated
	 */
	EAttribute getActivity_Id();

	/**
	 * Returns the meta object for the attribute '{@link com.project.mde.learning.Activity#getTitle <em>Title</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Title</em>'.
	 * @see com.project.mde.learning.Activity#getTitle()
	 * @see #getActivity()
	 * @generated
	 */
	EAttribute getActivity_Title();

	/**
	 * Returns the meta object for the reference '{@link com.project.mde.learning.Activity#getConcept <em>Concept</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Concept</em>'.
	 * @see com.project.mde.learning.Activity#getConcept()
	 * @see #getActivity()
	 * @generated
	 */
	EReference getActivity_Concept();

	/**
	 * Returns the meta object for the reference list '{@link com.project.mde.learning.Activity#getLearningObjectives <em>Learning Objectives</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Learning Objectives</em>'.
	 * @see com.project.mde.learning.Activity#getLearningObjectives()
	 * @see #getActivity()
	 * @generated
	 */
	EReference getActivity_LearningObjectives();

	/**
	 * Returns the meta object for class '{@link com.project.mde.learning.Attempt <em>Attempt</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Attempt</em>'.
	 * @see com.project.mde.learning.Attempt
	 * @generated
	 */
	EClass getAttempt();

	/**
	 * Returns the meta object for the attribute '{@link com.project.mde.learning.Attempt#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see com.project.mde.learning.Attempt#getId()
	 * @see #getAttempt()
	 * @generated
	 */
	EAttribute getAttempt_Id();

	/**
	 * Returns the meta object for the reference '{@link com.project.mde.learning.Attempt#getStudent <em>Student</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Student</em>'.
	 * @see com.project.mde.learning.Attempt#getStudent()
	 * @see #getAttempt()
	 * @generated
	 */
	EReference getAttempt_Student();

	/**
	 * Returns the meta object for the reference '{@link com.project.mde.learning.Attempt#getActivity <em>Activity</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Activity</em>'.
	 * @see com.project.mde.learning.Attempt#getActivity()
	 * @see #getAttempt()
	 * @generated
	 */
	EReference getAttempt_Activity();

	/**
	 * Returns the meta object for the reference '{@link com.project.mde.learning.Attempt#getConcept <em>Concept</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Concept</em>'.
	 * @see com.project.mde.learning.Attempt#getConcept()
	 * @see #getAttempt()
	 * @generated
	 */
	EReference getAttempt_Concept();

	/**
	 * Returns the meta object for the attribute '{@link com.project.mde.learning.Attempt#isSuccessful <em>Successful</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Successful</em>'.
	 * @see com.project.mde.learning.Attempt#isSuccessful()
	 * @see #getAttempt()
	 * @generated
	 */
	EAttribute getAttempt_Successful();

	/**
	 * Returns the meta object for the attribute '{@link com.project.mde.learning.Attempt#isFunctionalPassed <em>Functional Passed</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Functional Passed</em>'.
	 * @see com.project.mde.learning.Attempt#isFunctionalPassed()
	 * @see #getAttempt()
	 * @generated
	 */
	EAttribute getAttempt_FunctionalPassed();

	/**
	 * Returns the meta object for the attribute '{@link com.project.mde.learning.Attempt#getResolutionTime <em>Resolution Time</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Resolution Time</em>'.
	 * @see com.project.mde.learning.Attempt#getResolutionTime()
	 * @see #getAttempt()
	 * @generated
	 */
	EAttribute getAttempt_ResolutionTime();

	/**
	 * Returns the meta object for the attribute '{@link com.project.mde.learning.Attempt#getHintCount <em>Hint Count</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Hint Count</em>'.
	 * @see com.project.mde.learning.Attempt#getHintCount()
	 * @see #getAttempt()
	 * @generated
	 */
	EAttribute getAttempt_HintCount();

	/**
	 * Returns the meta object for the attribute '{@link com.project.mde.learning.Attempt#getSubmittedAt <em>Submitted At</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Submitted At</em>'.
	 * @see com.project.mde.learning.Attempt#getSubmittedAt()
	 * @see #getAttempt()
	 * @generated
	 */
	EAttribute getAttempt_SubmittedAt();

	/**
	 * Returns the meta object for the reference list '{@link com.project.mde.learning.Attempt#getErrorPatterns <em>Error Patterns</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Error Patterns</em>'.
	 * @see com.project.mde.learning.Attempt#getErrorPatterns()
	 * @see #getAttempt()
	 * @generated
	 */
	EReference getAttempt_ErrorPatterns();

	/**
	 * Returns the meta object for class '{@link com.project.mde.learning.ErrorPattern <em>Error Pattern</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Error Pattern</em>'.
	 * @see com.project.mde.learning.ErrorPattern
	 * @generated
	 */
	EClass getErrorPattern();

	/**
	 * Returns the meta object for the attribute '{@link com.project.mde.learning.ErrorPattern#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see com.project.mde.learning.ErrorPattern#getId()
	 * @see #getErrorPattern()
	 * @generated
	 */
	EAttribute getErrorPattern_Id();

	/**
	 * Returns the meta object for the reference '{@link com.project.mde.learning.ErrorPattern#getConcept <em>Concept</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Concept</em>'.
	 * @see com.project.mde.learning.ErrorPattern#getConcept()
	 * @see #getErrorPattern()
	 * @generated
	 */
	EReference getErrorPattern_Concept();

	/**
	 * Returns the meta object for the attribute '{@link com.project.mde.learning.ErrorPattern#getSeverity <em>Severity</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Severity</em>'.
	 * @see com.project.mde.learning.ErrorPattern#getSeverity()
	 * @see #getErrorPattern()
	 * @generated
	 */
	EAttribute getErrorPattern_Severity();

	/**
	 * Returns the meta object for the attribute '{@link com.project.mde.learning.ErrorPattern#getPedagogicalMeaning <em>Pedagogical Meaning</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Pedagogical Meaning</em>'.
	 * @see com.project.mde.learning.ErrorPattern#getPedagogicalMeaning()
	 * @see #getErrorPattern()
	 * @generated
	 */
	EAttribute getErrorPattern_PedagogicalMeaning();

	/**
	 * Returns the meta object for class '{@link com.project.mde.learning.HintUsage <em>Hint Usage</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Hint Usage</em>'.
	 * @see com.project.mde.learning.HintUsage
	 * @generated
	 */
	EClass getHintUsage();

	/**
	 * Returns the meta object for the attribute '{@link com.project.mde.learning.HintUsage#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see com.project.mde.learning.HintUsage#getId()
	 * @see #getHintUsage()
	 * @generated
	 */
	EAttribute getHintUsage_Id();

	/**
	 * Returns the meta object for the reference '{@link com.project.mde.learning.HintUsage#getStudent <em>Student</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Student</em>'.
	 * @see com.project.mde.learning.HintUsage#getStudent()
	 * @see #getHintUsage()
	 * @generated
	 */
	EReference getHintUsage_Student();

	/**
	 * Returns the meta object for the reference '{@link com.project.mde.learning.HintUsage#getActivity <em>Activity</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Activity</em>'.
	 * @see com.project.mde.learning.HintUsage#getActivity()
	 * @see #getHintUsage()
	 * @generated
	 */
	EReference getHintUsage_Activity();

	/**
	 * Returns the meta object for the attribute '{@link com.project.mde.learning.HintUsage#getHintLevel <em>Hint Level</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Hint Level</em>'.
	 * @see com.project.mde.learning.HintUsage#getHintLevel()
	 * @see #getHintUsage()
	 * @generated
	 */
	EAttribute getHintUsage_HintLevel();

	/**
	 * Returns the meta object for the attribute '{@link com.project.mde.learning.HintUsage#getUsedAt <em>Used At</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Used At</em>'.
	 * @see com.project.mde.learning.HintUsage#getUsedAt()
	 * @see #getHintUsage()
	 * @generated
	 */
	EAttribute getHintUsage_UsedAt();

	/**
	 * Returns the meta object for class '{@link com.project.mde.learning.Progress <em>Progress</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Progress</em>'.
	 * @see com.project.mde.learning.Progress
	 * @generated
	 */
	EClass getProgress();

	/**
	 * Returns the meta object for the reference '{@link com.project.mde.learning.Progress#getActivity <em>Activity</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Activity</em>'.
	 * @see com.project.mde.learning.Progress#getActivity()
	 * @see #getProgress()
	 * @generated
	 */
	EReference getProgress_Activity();

	/**
	 * Returns the meta object for the reference '{@link com.project.mde.learning.Progress#getConcept <em>Concept</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Concept</em>'.
	 * @see com.project.mde.learning.Progress#getConcept()
	 * @see #getProgress()
	 * @generated
	 */
	EReference getProgress_Concept();

	/**
	 * Returns the meta object for the attribute '{@link com.project.mde.learning.Progress#isCompleted <em>Completed</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Completed</em>'.
	 * @see com.project.mde.learning.Progress#isCompleted()
	 * @see #getProgress()
	 * @generated
	 */
	EAttribute getProgress_Completed();

	/**
	 * Returns the meta object for the attribute '{@link com.project.mde.learning.Progress#isUnlocked <em>Unlocked</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Unlocked</em>'.
	 * @see com.project.mde.learning.Progress#isUnlocked()
	 * @see #getProgress()
	 * @generated
	 */
	EAttribute getProgress_Unlocked();

	/**
	 * Returns the meta object for the attribute '{@link com.project.mde.learning.Progress#getCompletedAt <em>Completed At</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Completed At</em>'.
	 * @see com.project.mde.learning.Progress#getCompletedAt()
	 * @see #getProgress()
	 * @generated
	 */
	EAttribute getProgress_CompletedAt();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	LearningFactory getLearningFactory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each operation of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals {
		/**
		 * The meta object literal for the '{@link com.project.mde.learning.impl.StudentImpl <em>Student</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.project.mde.learning.impl.StudentImpl
		 * @see com.project.mde.learning.impl.LearningPackageImpl#getStudent()
		 * @generated
		 */
		EClass STUDENT = eINSTANCE.getStudent();

		/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute STUDENT__ID = eINSTANCE.getStudent_Id();

		/**
		 * The meta object literal for the '<em><b>Display Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute STUDENT__DISPLAY_NAME = eINSTANCE.getStudent_DisplayName();

		/**
		 * The meta object literal for the '<em><b>Created At</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute STUDENT__CREATED_AT = eINSTANCE.getStudent_CreatedAt();

		/**
		 * The meta object literal for the '{@link com.project.mde.learning.impl.StudentModelImpl <em>Student Model</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.project.mde.learning.impl.StudentModelImpl
		 * @see com.project.mde.learning.impl.LearningPackageImpl#getStudentModel()
		 * @generated
		 */
		EClass STUDENT_MODEL = eINSTANCE.getStudentModel();

		/**
		 * The meta object literal for the '<em><b>Model Version</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute STUDENT_MODEL__MODEL_VERSION = eINSTANCE.getStudentModel_ModelVersion();

		/**
		 * The meta object literal for the '<em><b>Last Updated</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute STUDENT_MODEL__LAST_UPDATED = eINSTANCE.getStudentModel_LastUpdated();

		/**
		 * The meta object literal for the '<em><b>Student</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference STUDENT_MODEL__STUDENT = eINSTANCE.getStudentModel_Student();

		/**
		 * The meta object literal for the '<em><b>Concepts</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference STUDENT_MODEL__CONCEPTS = eINSTANCE.getStudentModel_Concepts();

		/**
		 * The meta object literal for the '<em><b>Activities</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference STUDENT_MODEL__ACTIVITIES = eINSTANCE.getStudentModel_Activities();

		/**
		 * The meta object literal for the '<em><b>Learning Objectives</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference STUDENT_MODEL__LEARNING_OBJECTIVES = eINSTANCE.getStudentModel_LearningObjectives();

		/**
		 * The meta object literal for the '<em><b>Error Patterns</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference STUDENT_MODEL__ERROR_PATTERNS = eINSTANCE.getStudentModel_ErrorPatterns();

		/**
		 * The meta object literal for the '<em><b>Concept Masteries</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference STUDENT_MODEL__CONCEPT_MASTERIES = eINSTANCE.getStudentModel_ConceptMasteries();

		/**
		 * The meta object literal for the '<em><b>Attempts</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference STUDENT_MODEL__ATTEMPTS = eINSTANCE.getStudentModel_Attempts();

		/**
		 * The meta object literal for the '<em><b>Hint Usages</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference STUDENT_MODEL__HINT_USAGES = eINSTANCE.getStudentModel_HintUsages();

		/**
		 * The meta object literal for the '<em><b>Progress</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference STUDENT_MODEL__PROGRESS = eINSTANCE.getStudentModel_Progress();

		/**
		 * The meta object literal for the '{@link com.project.mde.learning.impl.ConceptImpl <em>Concept</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.project.mde.learning.impl.ConceptImpl
		 * @see com.project.mde.learning.impl.LearningPackageImpl#getConcept()
		 * @generated
		 */
		EClass CONCEPT = eINSTANCE.getConcept();

		/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONCEPT__ID = eINSTANCE.getConcept_Id();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONCEPT__NAME = eINSTANCE.getConcept_Name();

		/**
		 * The meta object literal for the '<em><b>Prerequisites</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONCEPT__PREREQUISITES = eINSTANCE.getConcept_Prerequisites();

		/**
		 * The meta object literal for the '<em><b>Minimum Mastery To Unlock</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONCEPT__MINIMUM_MASTERY_TO_UNLOCK = eINSTANCE.getConcept_MinimumMasteryToUnlock();

		/**
		 * The meta object literal for the '<em><b>Activities</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONCEPT__ACTIVITIES = eINSTANCE.getConcept_Activities();

		/**
		 * The meta object literal for the '<em><b>Reinforcement Activities</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONCEPT__REINFORCEMENT_ACTIVITIES = eINSTANCE.getConcept_ReinforcementActivities();

		/**
		 * The meta object literal for the '{@link com.project.mde.learning.impl.ConceptMasteryImpl <em>Concept Mastery</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.project.mde.learning.impl.ConceptMasteryImpl
		 * @see com.project.mde.learning.impl.LearningPackageImpl#getConceptMastery()
		 * @generated
		 */
		EClass CONCEPT_MASTERY = eINSTANCE.getConceptMastery();

		/**
		 * The meta object literal for the '<em><b>Student</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONCEPT_MASTERY__STUDENT = eINSTANCE.getConceptMastery_Student();

		/**
		 * The meta object literal for the '<em><b>Concept</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONCEPT_MASTERY__CONCEPT = eINSTANCE.getConceptMastery_Concept();

		/**
		 * The meta object literal for the '<em><b>Mastery Score</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONCEPT_MASTERY__MASTERY_SCORE = eINSTANCE.getConceptMastery_MasteryScore();

		/**
		 * The meta object literal for the '<em><b>Attempt Count</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONCEPT_MASTERY__ATTEMPT_COUNT = eINSTANCE.getConceptMastery_AttemptCount();

		/**
		 * The meta object literal for the '<em><b>Success Count</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONCEPT_MASTERY__SUCCESS_COUNT = eINSTANCE.getConceptMastery_SuccessCount();

		/**
		 * The meta object literal for the '<em><b>Failure Count</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONCEPT_MASTERY__FAILURE_COUNT = eINSTANCE.getConceptMastery_FailureCount();

		/**
		 * The meta object literal for the '<em><b>Consecutive Failures</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONCEPT_MASTERY__CONSECUTIVE_FAILURES = eINSTANCE.getConceptMastery_ConsecutiveFailures();

		/**
		 * The meta object literal for the '<em><b>Hint Count</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONCEPT_MASTERY__HINT_COUNT = eINSTANCE.getConceptMastery_HintCount();

		/**
		 * The meta object literal for the '<em><b>Average Resolution Time</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONCEPT_MASTERY__AVERAGE_RESOLUTION_TIME = eINSTANCE.getConceptMastery_AverageResolutionTime();

		/**
		 * The meta object literal for the '<em><b>Last Updated</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONCEPT_MASTERY__LAST_UPDATED = eINSTANCE.getConceptMastery_LastUpdated();

		/**
		 * The meta object literal for the '<em><b>Recent Error Patterns</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONCEPT_MASTERY__RECENT_ERROR_PATTERNS = eINSTANCE.getConceptMastery_RecentErrorPatterns();

		/**
		 * The meta object literal for the '{@link com.project.mde.learning.impl.LearningObjectiveImpl <em>Objective</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.project.mde.learning.impl.LearningObjectiveImpl
		 * @see com.project.mde.learning.impl.LearningPackageImpl#getLearningObjective()
		 * @generated
		 */
		EClass LEARNING_OBJECTIVE = eINSTANCE.getLearningObjective();

		/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LEARNING_OBJECTIVE__ID = eINSTANCE.getLearningObjective_Id();

		/**
		 * The meta object literal for the '<em><b>Description</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LEARNING_OBJECTIVE__DESCRIPTION = eINSTANCE.getLearningObjective_Description();

		/**
		 * The meta object literal for the '<em><b>Concept</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference LEARNING_OBJECTIVE__CONCEPT = eINSTANCE.getLearningObjective_Concept();

		/**
		 * The meta object literal for the '{@link com.project.mde.learning.impl.ActivityImpl <em>Activity</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.project.mde.learning.impl.ActivityImpl
		 * @see com.project.mde.learning.impl.LearningPackageImpl#getActivity()
		 * @generated
		 */
		EClass ACTIVITY = eINSTANCE.getActivity();

		/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ACTIVITY__ID = eINSTANCE.getActivity_Id();

		/**
		 * The meta object literal for the '<em><b>Title</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ACTIVITY__TITLE = eINSTANCE.getActivity_Title();

		/**
		 * The meta object literal for the '<em><b>Concept</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ACTIVITY__CONCEPT = eINSTANCE.getActivity_Concept();

		/**
		 * The meta object literal for the '<em><b>Learning Objectives</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ACTIVITY__LEARNING_OBJECTIVES = eINSTANCE.getActivity_LearningObjectives();

		/**
		 * The meta object literal for the '{@link com.project.mde.learning.impl.AttemptImpl <em>Attempt</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.project.mde.learning.impl.AttemptImpl
		 * @see com.project.mde.learning.impl.LearningPackageImpl#getAttempt()
		 * @generated
		 */
		EClass ATTEMPT = eINSTANCE.getAttempt();

		/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ATTEMPT__ID = eINSTANCE.getAttempt_Id();

		/**
		 * The meta object literal for the '<em><b>Student</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ATTEMPT__STUDENT = eINSTANCE.getAttempt_Student();

		/**
		 * The meta object literal for the '<em><b>Activity</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ATTEMPT__ACTIVITY = eINSTANCE.getAttempt_Activity();

		/**
		 * The meta object literal for the '<em><b>Concept</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ATTEMPT__CONCEPT = eINSTANCE.getAttempt_Concept();

		/**
		 * The meta object literal for the '<em><b>Successful</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ATTEMPT__SUCCESSFUL = eINSTANCE.getAttempt_Successful();

		/**
		 * The meta object literal for the '<em><b>Functional Passed</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ATTEMPT__FUNCTIONAL_PASSED = eINSTANCE.getAttempt_FunctionalPassed();

		/**
		 * The meta object literal for the '<em><b>Resolution Time</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ATTEMPT__RESOLUTION_TIME = eINSTANCE.getAttempt_ResolutionTime();

		/**
		 * The meta object literal for the '<em><b>Hint Count</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ATTEMPT__HINT_COUNT = eINSTANCE.getAttempt_HintCount();

		/**
		 * The meta object literal for the '<em><b>Submitted At</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ATTEMPT__SUBMITTED_AT = eINSTANCE.getAttempt_SubmittedAt();

		/**
		 * The meta object literal for the '<em><b>Error Patterns</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ATTEMPT__ERROR_PATTERNS = eINSTANCE.getAttempt_ErrorPatterns();

		/**
		 * The meta object literal for the '{@link com.project.mde.learning.impl.ErrorPatternImpl <em>Error Pattern</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.project.mde.learning.impl.ErrorPatternImpl
		 * @see com.project.mde.learning.impl.LearningPackageImpl#getErrorPattern()
		 * @generated
		 */
		EClass ERROR_PATTERN = eINSTANCE.getErrorPattern();

		/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ERROR_PATTERN__ID = eINSTANCE.getErrorPattern_Id();

		/**
		 * The meta object literal for the '<em><b>Concept</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ERROR_PATTERN__CONCEPT = eINSTANCE.getErrorPattern_Concept();

		/**
		 * The meta object literal for the '<em><b>Severity</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ERROR_PATTERN__SEVERITY = eINSTANCE.getErrorPattern_Severity();

		/**
		 * The meta object literal for the '<em><b>Pedagogical Meaning</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ERROR_PATTERN__PEDAGOGICAL_MEANING = eINSTANCE.getErrorPattern_PedagogicalMeaning();

		/**
		 * The meta object literal for the '{@link com.project.mde.learning.impl.HintUsageImpl <em>Hint Usage</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.project.mde.learning.impl.HintUsageImpl
		 * @see com.project.mde.learning.impl.LearningPackageImpl#getHintUsage()
		 * @generated
		 */
		EClass HINT_USAGE = eINSTANCE.getHintUsage();

		/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute HINT_USAGE__ID = eINSTANCE.getHintUsage_Id();

		/**
		 * The meta object literal for the '<em><b>Student</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference HINT_USAGE__STUDENT = eINSTANCE.getHintUsage_Student();

		/**
		 * The meta object literal for the '<em><b>Activity</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference HINT_USAGE__ACTIVITY = eINSTANCE.getHintUsage_Activity();

		/**
		 * The meta object literal for the '<em><b>Hint Level</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute HINT_USAGE__HINT_LEVEL = eINSTANCE.getHintUsage_HintLevel();

		/**
		 * The meta object literal for the '<em><b>Used At</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute HINT_USAGE__USED_AT = eINSTANCE.getHintUsage_UsedAt();

		/**
		 * The meta object literal for the '{@link com.project.mde.learning.impl.ProgressImpl <em>Progress</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.project.mde.learning.impl.ProgressImpl
		 * @see com.project.mde.learning.impl.LearningPackageImpl#getProgress()
		 * @generated
		 */
		EClass PROGRESS = eINSTANCE.getProgress();

		/**
		 * The meta object literal for the '<em><b>Activity</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PROGRESS__ACTIVITY = eINSTANCE.getProgress_Activity();

		/**
		 * The meta object literal for the '<em><b>Concept</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PROGRESS__CONCEPT = eINSTANCE.getProgress_Concept();

		/**
		 * The meta object literal for the '<em><b>Completed</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PROGRESS__COMPLETED = eINSTANCE.getProgress_Completed();

		/**
		 * The meta object literal for the '<em><b>Unlocked</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PROGRESS__UNLOCKED = eINSTANCE.getProgress_Unlocked();

		/**
		 * The meta object literal for the '<em><b>Completed At</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PROGRESS__COMPLETED_AT = eINSTANCE.getProgress_CompletedAt();

	}

} //LearningPackage

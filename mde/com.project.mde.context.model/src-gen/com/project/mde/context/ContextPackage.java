/**
 */
package com.project.mde.context;

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
 * @see com.project.mde.context.ContextFactory
 * @model kind="package"
 * @generated
 */
public interface ContextPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "context";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "https://mdedu.espoch.edu.ec/model/context/1.0";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "context";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	ContextPackage eINSTANCE = com.project.mde.context.impl.ContextPackageImpl.init();

	/**
	 * The meta object id for the '{@link com.project.mde.context.impl.ContextModelImpl <em>Model</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.project.mde.context.impl.ContextModelImpl
	 * @see com.project.mde.context.impl.ContextPackageImpl#getContextModel()
	 * @generated
	 */
	int CONTEXT_MODEL = 0;

	/**
	 * The feature id for the '<em><b>Model Version</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTEXT_MODEL__MODEL_VERSION = 0;

	/**
	 * The feature id for the '<em><b>Student Context</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTEXT_MODEL__STUDENT_CONTEXT = 1;

	/**
	 * The feature id for the '<em><b>Platform Context</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTEXT_MODEL__PLATFORM_CONTEXT = 2;

	/**
	 * The feature id for the '<em><b>Environment Context</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTEXT_MODEL__ENVIRONMENT_CONTEXT = 3;

	/**
	 * The number of structural features of the '<em>Model</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTEXT_MODEL_FEATURE_COUNT = 4;

	/**
	 * The number of operations of the '<em>Model</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTEXT_MODEL_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link com.project.mde.context.impl.StudentContextImpl <em>Student Context</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.project.mde.context.impl.StudentContextImpl
	 * @see com.project.mde.context.impl.ContextPackageImpl#getStudentContext()
	 * @generated
	 */
	int STUDENT_CONTEXT = 1;

	/**
	 * The feature id for the '<em><b>Student Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STUDENT_CONTEXT__STUDENT_ID = 0;

	/**
	 * The feature id for the '<em><b>Concept Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STUDENT_CONTEXT__CONCEPT_ID = 1;

	/**
	 * The feature id for the '<em><b>Activity Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STUDENT_CONTEXT__ACTIVITY_ID = 2;

	/**
	 * The feature id for the '<em><b>Mastery Score</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STUDENT_CONTEXT__MASTERY_SCORE = 3;

	/**
	 * The feature id for the '<em><b>Attempt Count</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STUDENT_CONTEXT__ATTEMPT_COUNT = 4;

	/**
	 * The feature id for the '<em><b>Success Count</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STUDENT_CONTEXT__SUCCESS_COUNT = 5;

	/**
	 * The feature id for the '<em><b>Failure Count</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STUDENT_CONTEXT__FAILURE_COUNT = 6;

	/**
	 * The feature id for the '<em><b>Consecutive Failures</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STUDENT_CONTEXT__CONSECUTIVE_FAILURES = 7;

	/**
	 * The feature id for the '<em><b>Hint Count</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STUDENT_CONTEXT__HINT_COUNT = 8;

	/**
	 * The feature id for the '<em><b>Average Resolution Time</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STUDENT_CONTEXT__AVERAGE_RESOLUTION_TIME = 9;

	/**
	 * The feature id for the '<em><b>Current Resolution Time</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STUDENT_CONTEXT__CURRENT_RESOLUTION_TIME = 10;

	/**
	 * The feature id for the '<em><b>Activity Passed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STUDENT_CONTEXT__ACTIVITY_PASSED = 11;

	/**
	 * The feature id for the '<em><b>Functional Passed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STUDENT_CONTEXT__FUNCTIONAL_PASSED = 12;

	/**
	 * The feature id for the '<em><b>Required Concept Used</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STUDENT_CONTEXT__REQUIRED_CONCEPT_USED = 13;

	/**
	 * The feature id for the '<em><b>Detected Patterns</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STUDENT_CONTEXT__DETECTED_PATTERNS = 14;

	/**
	 * The feature id for the '<em><b>Repeated Error Pattern</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STUDENT_CONTEXT__REPEATED_ERROR_PATTERN = 15;

	/**
	 * The number of structural features of the '<em>Student Context</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STUDENT_CONTEXT_FEATURE_COUNT = 16;

	/**
	 * The number of operations of the '<em>Student Context</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STUDENT_CONTEXT_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link com.project.mde.context.impl.PlatformContextImpl <em>Platform Context</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.project.mde.context.impl.PlatformContextImpl
	 * @see com.project.mde.context.impl.ContextPackageImpl#getPlatformContext()
	 * @generated
	 */
	int PLATFORM_CONTEXT = 2;

	/**
	 * The feature id for the '<em><b>Platform</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PLATFORM_CONTEXT__PLATFORM = 0;

	/**
	 * The number of structural features of the '<em>Platform Context</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PLATFORM_CONTEXT_FEATURE_COUNT = 1;

	/**
	 * The number of operations of the '<em>Platform Context</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PLATFORM_CONTEXT_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link com.project.mde.context.impl.EnvironmentContextImpl <em>Environment Context</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.project.mde.context.impl.EnvironmentContextImpl
	 * @see com.project.mde.context.impl.ContextPackageImpl#getEnvironmentContext()
	 * @generated
	 */
	int ENVIRONMENT_CONTEXT = 3;

	/**
	 * The feature id for the '<em><b>Level Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENVIRONMENT_CONTEXT__LEVEL_ID = 0;

	/**
	 * The feature id for the '<em><b>Concept Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENVIRONMENT_CONTEXT__CONCEPT_ID = 1;

	/**
	 * The feature id for the '<em><b>Activity Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENVIRONMENT_CONTEXT__ACTIVITY_ID = 2;

	/**
	 * The number of structural features of the '<em>Environment Context</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENVIRONMENT_CONTEXT_FEATURE_COUNT = 3;

	/**
	 * The number of operations of the '<em>Environment Context</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENVIRONMENT_CONTEXT_OPERATION_COUNT = 0;


	/**
	 * Returns the meta object for class '{@link com.project.mde.context.ContextModel <em>Model</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Model</em>'.
	 * @see com.project.mde.context.ContextModel
	 * @generated
	 */
	EClass getContextModel();

	/**
	 * Returns the meta object for the attribute '{@link com.project.mde.context.ContextModel#getModelVersion <em>Model Version</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Model Version</em>'.
	 * @see com.project.mde.context.ContextModel#getModelVersion()
	 * @see #getContextModel()
	 * @generated
	 */
	EAttribute getContextModel_ModelVersion();

	/**
	 * Returns the meta object for the containment reference '{@link com.project.mde.context.ContextModel#getStudentContext <em>Student Context</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Student Context</em>'.
	 * @see com.project.mde.context.ContextModel#getStudentContext()
	 * @see #getContextModel()
	 * @generated
	 */
	EReference getContextModel_StudentContext();

	/**
	 * Returns the meta object for the containment reference '{@link com.project.mde.context.ContextModel#getPlatformContext <em>Platform Context</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Platform Context</em>'.
	 * @see com.project.mde.context.ContextModel#getPlatformContext()
	 * @see #getContextModel()
	 * @generated
	 */
	EReference getContextModel_PlatformContext();

	/**
	 * Returns the meta object for the containment reference '{@link com.project.mde.context.ContextModel#getEnvironmentContext <em>Environment Context</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Environment Context</em>'.
	 * @see com.project.mde.context.ContextModel#getEnvironmentContext()
	 * @see #getContextModel()
	 * @generated
	 */
	EReference getContextModel_EnvironmentContext();

	/**
	 * Returns the meta object for class '{@link com.project.mde.context.StudentContext <em>Student Context</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Student Context</em>'.
	 * @see com.project.mde.context.StudentContext
	 * @generated
	 */
	EClass getStudentContext();

	/**
	 * Returns the meta object for the attribute '{@link com.project.mde.context.StudentContext#getStudentId <em>Student Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Student Id</em>'.
	 * @see com.project.mde.context.StudentContext#getStudentId()
	 * @see #getStudentContext()
	 * @generated
	 */
	EAttribute getStudentContext_StudentId();

	/**
	 * Returns the meta object for the attribute '{@link com.project.mde.context.StudentContext#getConceptId <em>Concept Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Concept Id</em>'.
	 * @see com.project.mde.context.StudentContext#getConceptId()
	 * @see #getStudentContext()
	 * @generated
	 */
	EAttribute getStudentContext_ConceptId();

	/**
	 * Returns the meta object for the attribute '{@link com.project.mde.context.StudentContext#getActivityId <em>Activity Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Activity Id</em>'.
	 * @see com.project.mde.context.StudentContext#getActivityId()
	 * @see #getStudentContext()
	 * @generated
	 */
	EAttribute getStudentContext_ActivityId();

	/**
	 * Returns the meta object for the attribute '{@link com.project.mde.context.StudentContext#getMasteryScore <em>Mastery Score</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Mastery Score</em>'.
	 * @see com.project.mde.context.StudentContext#getMasteryScore()
	 * @see #getStudentContext()
	 * @generated
	 */
	EAttribute getStudentContext_MasteryScore();

	/**
	 * Returns the meta object for the attribute '{@link com.project.mde.context.StudentContext#getAttemptCount <em>Attempt Count</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Attempt Count</em>'.
	 * @see com.project.mde.context.StudentContext#getAttemptCount()
	 * @see #getStudentContext()
	 * @generated
	 */
	EAttribute getStudentContext_AttemptCount();

	/**
	 * Returns the meta object for the attribute '{@link com.project.mde.context.StudentContext#getSuccessCount <em>Success Count</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Success Count</em>'.
	 * @see com.project.mde.context.StudentContext#getSuccessCount()
	 * @see #getStudentContext()
	 * @generated
	 */
	EAttribute getStudentContext_SuccessCount();

	/**
	 * Returns the meta object for the attribute '{@link com.project.mde.context.StudentContext#getFailureCount <em>Failure Count</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Failure Count</em>'.
	 * @see com.project.mde.context.StudentContext#getFailureCount()
	 * @see #getStudentContext()
	 * @generated
	 */
	EAttribute getStudentContext_FailureCount();

	/**
	 * Returns the meta object for the attribute '{@link com.project.mde.context.StudentContext#getConsecutiveFailures <em>Consecutive Failures</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Consecutive Failures</em>'.
	 * @see com.project.mde.context.StudentContext#getConsecutiveFailures()
	 * @see #getStudentContext()
	 * @generated
	 */
	EAttribute getStudentContext_ConsecutiveFailures();

	/**
	 * Returns the meta object for the attribute '{@link com.project.mde.context.StudentContext#getHintCount <em>Hint Count</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Hint Count</em>'.
	 * @see com.project.mde.context.StudentContext#getHintCount()
	 * @see #getStudentContext()
	 * @generated
	 */
	EAttribute getStudentContext_HintCount();

	/**
	 * Returns the meta object for the attribute '{@link com.project.mde.context.StudentContext#getAverageResolutionTime <em>Average Resolution Time</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Average Resolution Time</em>'.
	 * @see com.project.mde.context.StudentContext#getAverageResolutionTime()
	 * @see #getStudentContext()
	 * @generated
	 */
	EAttribute getStudentContext_AverageResolutionTime();

	/**
	 * Returns the meta object for the attribute '{@link com.project.mde.context.StudentContext#getCurrentResolutionTime <em>Current Resolution Time</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Current Resolution Time</em>'.
	 * @see com.project.mde.context.StudentContext#getCurrentResolutionTime()
	 * @see #getStudentContext()
	 * @generated
	 */
	EAttribute getStudentContext_CurrentResolutionTime();

	/**
	 * Returns the meta object for the attribute '{@link com.project.mde.context.StudentContext#isActivityPassed <em>Activity Passed</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Activity Passed</em>'.
	 * @see com.project.mde.context.StudentContext#isActivityPassed()
	 * @see #getStudentContext()
	 * @generated
	 */
	EAttribute getStudentContext_ActivityPassed();

	/**
	 * Returns the meta object for the attribute '{@link com.project.mde.context.StudentContext#isFunctionalPassed <em>Functional Passed</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Functional Passed</em>'.
	 * @see com.project.mde.context.StudentContext#isFunctionalPassed()
	 * @see #getStudentContext()
	 * @generated
	 */
	EAttribute getStudentContext_FunctionalPassed();

	/**
	 * Returns the meta object for the attribute '{@link com.project.mde.context.StudentContext#isRequiredConceptUsed <em>Required Concept Used</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Required Concept Used</em>'.
	 * @see com.project.mde.context.StudentContext#isRequiredConceptUsed()
	 * @see #getStudentContext()
	 * @generated
	 */
	EAttribute getStudentContext_RequiredConceptUsed();

	/**
	 * Returns the meta object for the attribute list '{@link com.project.mde.context.StudentContext#getDetectedPatterns <em>Detected Patterns</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Detected Patterns</em>'.
	 * @see com.project.mde.context.StudentContext#getDetectedPatterns()
	 * @see #getStudentContext()
	 * @generated
	 */
	EAttribute getStudentContext_DetectedPatterns();

	/**
	 * Returns the meta object for the attribute '{@link com.project.mde.context.StudentContext#isRepeatedErrorPattern <em>Repeated Error Pattern</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Repeated Error Pattern</em>'.
	 * @see com.project.mde.context.StudentContext#isRepeatedErrorPattern()
	 * @see #getStudentContext()
	 * @generated
	 */
	EAttribute getStudentContext_RepeatedErrorPattern();

	/**
	 * Returns the meta object for class '{@link com.project.mde.context.PlatformContext <em>Platform Context</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Platform Context</em>'.
	 * @see com.project.mde.context.PlatformContext
	 * @generated
	 */
	EClass getPlatformContext();

	/**
	 * Returns the meta object for the attribute '{@link com.project.mde.context.PlatformContext#getPlatform <em>Platform</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Platform</em>'.
	 * @see com.project.mde.context.PlatformContext#getPlatform()
	 * @see #getPlatformContext()
	 * @generated
	 */
	EAttribute getPlatformContext_Platform();

	/**
	 * Returns the meta object for class '{@link com.project.mde.context.EnvironmentContext <em>Environment Context</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Environment Context</em>'.
	 * @see com.project.mde.context.EnvironmentContext
	 * @generated
	 */
	EClass getEnvironmentContext();

	/**
	 * Returns the meta object for the attribute '{@link com.project.mde.context.EnvironmentContext#getLevelId <em>Level Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Level Id</em>'.
	 * @see com.project.mde.context.EnvironmentContext#getLevelId()
	 * @see #getEnvironmentContext()
	 * @generated
	 */
	EAttribute getEnvironmentContext_LevelId();

	/**
	 * Returns the meta object for the attribute '{@link com.project.mde.context.EnvironmentContext#getConceptId <em>Concept Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Concept Id</em>'.
	 * @see com.project.mde.context.EnvironmentContext#getConceptId()
	 * @see #getEnvironmentContext()
	 * @generated
	 */
	EAttribute getEnvironmentContext_ConceptId();

	/**
	 * Returns the meta object for the attribute '{@link com.project.mde.context.EnvironmentContext#getActivityId <em>Activity Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Activity Id</em>'.
	 * @see com.project.mde.context.EnvironmentContext#getActivityId()
	 * @see #getEnvironmentContext()
	 * @generated
	 */
	EAttribute getEnvironmentContext_ActivityId();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	ContextFactory getContextFactory();

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
		 * The meta object literal for the '{@link com.project.mde.context.impl.ContextModelImpl <em>Model</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.project.mde.context.impl.ContextModelImpl
		 * @see com.project.mde.context.impl.ContextPackageImpl#getContextModel()
		 * @generated
		 */
		EClass CONTEXT_MODEL = eINSTANCE.getContextModel();

		/**
		 * The meta object literal for the '<em><b>Model Version</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONTEXT_MODEL__MODEL_VERSION = eINSTANCE.getContextModel_ModelVersion();

		/**
		 * The meta object literal for the '<em><b>Student Context</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONTEXT_MODEL__STUDENT_CONTEXT = eINSTANCE.getContextModel_StudentContext();

		/**
		 * The meta object literal for the '<em><b>Platform Context</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONTEXT_MODEL__PLATFORM_CONTEXT = eINSTANCE.getContextModel_PlatformContext();

		/**
		 * The meta object literal for the '<em><b>Environment Context</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONTEXT_MODEL__ENVIRONMENT_CONTEXT = eINSTANCE.getContextModel_EnvironmentContext();

		/**
		 * The meta object literal for the '{@link com.project.mde.context.impl.StudentContextImpl <em>Student Context</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.project.mde.context.impl.StudentContextImpl
		 * @see com.project.mde.context.impl.ContextPackageImpl#getStudentContext()
		 * @generated
		 */
		EClass STUDENT_CONTEXT = eINSTANCE.getStudentContext();

		/**
		 * The meta object literal for the '<em><b>Student Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute STUDENT_CONTEXT__STUDENT_ID = eINSTANCE.getStudentContext_StudentId();

		/**
		 * The meta object literal for the '<em><b>Concept Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute STUDENT_CONTEXT__CONCEPT_ID = eINSTANCE.getStudentContext_ConceptId();

		/**
		 * The meta object literal for the '<em><b>Activity Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute STUDENT_CONTEXT__ACTIVITY_ID = eINSTANCE.getStudentContext_ActivityId();

		/**
		 * The meta object literal for the '<em><b>Mastery Score</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute STUDENT_CONTEXT__MASTERY_SCORE = eINSTANCE.getStudentContext_MasteryScore();

		/**
		 * The meta object literal for the '<em><b>Attempt Count</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute STUDENT_CONTEXT__ATTEMPT_COUNT = eINSTANCE.getStudentContext_AttemptCount();

		/**
		 * The meta object literal for the '<em><b>Success Count</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute STUDENT_CONTEXT__SUCCESS_COUNT = eINSTANCE.getStudentContext_SuccessCount();

		/**
		 * The meta object literal for the '<em><b>Failure Count</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute STUDENT_CONTEXT__FAILURE_COUNT = eINSTANCE.getStudentContext_FailureCount();

		/**
		 * The meta object literal for the '<em><b>Consecutive Failures</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute STUDENT_CONTEXT__CONSECUTIVE_FAILURES = eINSTANCE.getStudentContext_ConsecutiveFailures();

		/**
		 * The meta object literal for the '<em><b>Hint Count</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute STUDENT_CONTEXT__HINT_COUNT = eINSTANCE.getStudentContext_HintCount();

		/**
		 * The meta object literal for the '<em><b>Average Resolution Time</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute STUDENT_CONTEXT__AVERAGE_RESOLUTION_TIME = eINSTANCE.getStudentContext_AverageResolutionTime();

		/**
		 * The meta object literal for the '<em><b>Current Resolution Time</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute STUDENT_CONTEXT__CURRENT_RESOLUTION_TIME = eINSTANCE.getStudentContext_CurrentResolutionTime();

		/**
		 * The meta object literal for the '<em><b>Activity Passed</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute STUDENT_CONTEXT__ACTIVITY_PASSED = eINSTANCE.getStudentContext_ActivityPassed();

		/**
		 * The meta object literal for the '<em><b>Functional Passed</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute STUDENT_CONTEXT__FUNCTIONAL_PASSED = eINSTANCE.getStudentContext_FunctionalPassed();

		/**
		 * The meta object literal for the '<em><b>Required Concept Used</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute STUDENT_CONTEXT__REQUIRED_CONCEPT_USED = eINSTANCE.getStudentContext_RequiredConceptUsed();

		/**
		 * The meta object literal for the '<em><b>Detected Patterns</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute STUDENT_CONTEXT__DETECTED_PATTERNS = eINSTANCE.getStudentContext_DetectedPatterns();

		/**
		 * The meta object literal for the '<em><b>Repeated Error Pattern</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute STUDENT_CONTEXT__REPEATED_ERROR_PATTERN = eINSTANCE.getStudentContext_RepeatedErrorPattern();

		/**
		 * The meta object literal for the '{@link com.project.mde.context.impl.PlatformContextImpl <em>Platform Context</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.project.mde.context.impl.PlatformContextImpl
		 * @see com.project.mde.context.impl.ContextPackageImpl#getPlatformContext()
		 * @generated
		 */
		EClass PLATFORM_CONTEXT = eINSTANCE.getPlatformContext();

		/**
		 * The meta object literal for the '<em><b>Platform</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PLATFORM_CONTEXT__PLATFORM = eINSTANCE.getPlatformContext_Platform();

		/**
		 * The meta object literal for the '{@link com.project.mde.context.impl.EnvironmentContextImpl <em>Environment Context</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.project.mde.context.impl.EnvironmentContextImpl
		 * @see com.project.mde.context.impl.ContextPackageImpl#getEnvironmentContext()
		 * @generated
		 */
		EClass ENVIRONMENT_CONTEXT = eINSTANCE.getEnvironmentContext();

		/**
		 * The meta object literal for the '<em><b>Level Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ENVIRONMENT_CONTEXT__LEVEL_ID = eINSTANCE.getEnvironmentContext_LevelId();

		/**
		 * The meta object literal for the '<em><b>Concept Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ENVIRONMENT_CONTEXT__CONCEPT_ID = eINSTANCE.getEnvironmentContext_ConceptId();

		/**
		 * The meta object literal for the '<em><b>Activity Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ENVIRONMENT_CONTEXT__ACTIVITY_ID = eINSTANCE.getEnvironmentContext_ActivityId();

	}

} //ContextPackage

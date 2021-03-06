package timetablepuzzle.entities.administration;

import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.persistence.*;

import timetablepuzzle.entities.Solution;
import timetablepuzzle.entities.administration.Curricula.Term;

@Entity
@Table(name = "academic_sessions")
public class AcademicSession {
	/*********** Regular properties *************/
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "name")
	private String name;

	@Column(name = "term")
	@Enumerated(EnumType.STRING)
	private Term term;

	// When the session starts, the classes also start
	@Temporal(TemporalType.DATE)
	@Column(name = "session_start_date")
	private Calendar sessionStartDate;

	@Temporal(TemporalType.DATE)
	@Column(name = "classes_end_date")
	private Calendar classesEndDate;

	@Temporal(TemporalType.DATE)
	@Column(name = "exams_start_date")
	private Calendar examsStartDate;

	// When the session ends, the exams also end
	@Temporal(TemporalType.DATE)
	@Column(name = "session_end_date")
	private Calendar sessionEndDate;

	@OneToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH })
	@JoinColumn(name = "accepted_solution_id", unique = false)
	private Solution acceptedSolution;

	public AcademicSession() {
		this(0, "NoName", Term.UNASSIGNED, new GregorianCalendar(), new GregorianCalendar(), new GregorianCalendar(),
				new GregorianCalendar(), null);
	}

	public AcademicSession(int id, String name, Term term, Calendar sessionStartDate, Calendar classesEndDate,
			Calendar examsStartDate, Calendar sessionEndDate, Solution acceptedSolution) {
		this.id = id;
		setName(name);
		setTerm(term);
		setSessionStartDate(sessionStartDate);
		setClassesEndDate(classesEndDate);
		setExamsStartDate(examsStartDate);
		setSessionEndDate(sessionEndDate);
		setAcceptedSolution(acceptedSolution);
	}

	/*********************** Getters and setters *******************/
	public int getId() {
		return this.id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Term getTerm() {
		return term;
	}

	public void setTerm(Term term) {
		this.term = term;
	}

	public Calendar getSessionStartDate() {
		return sessionStartDate;
	}

	public void setSessionStartDate(Calendar sessionStartDate) {
		this.sessionStartDate = sessionStartDate;
	}

	public Calendar getClassesEndDate() {
		return classesEndDate;
	}

	public void setClassesEndDate(Calendar classesEndDate) {
		this.classesEndDate = classesEndDate;
	}

	public Calendar getExamsStartDate() {
		return examsStartDate;
	}

	public void setExamsStartDate(Calendar examsStartDate) {
		this.examsStartDate = examsStartDate;
	}

	public Calendar getSessionEndDate() {
		return sessionEndDate;
	}

	public void setSessionEndDate(Calendar sessionEndDate) {
		this.sessionEndDate = sessionEndDate;
	}

	public Solution getAcceptedSolution() {
		return acceptedSolution;
	}

	public void setAcceptedSolution(Solution acceptedSolution) {
		this.acceptedSolution = acceptedSolution;
	}

	public boolean hasSolution() {
		return this.acceptedSolution != null;
	}

	/*******************
	 * Methods that model the class behavior
	 *******************/
	@Override
	public String toString() {
		return String.format("%s(%s)", this.name, this.sessionStartDate.get(Calendar.YEAR));
	}

	@Override
	public boolean equals(Object o) {
		boolean equals = (o instanceof AcademicSession);
		if (equals) {
			AcademicSession other = (AcademicSession) o;
			equals = ((this.id == other.getId()) && (this.name.equals(other.getName()))
					&& (this.term.equals(other.getTerm()))
					&& (this.sessionStartDate.equals(other.getSessionStartDate()))
					&& (this.classesEndDate.equals(other.getClassesEndDate()))
					&& (this.examsStartDate.equals(other.getExamsStartDate()))
					&& (this.sessionEndDate.equals(other.getSessionEndDate())));
			if(this.acceptedSolution != null)
			{
				equals &= (this.acceptedSolution.equals(other.getAcceptedSolution()));
			}
		}

		return equals;
	}

	@Override
	public int hashCode() {
		return String.format("AcademicSession:%s:%s", Integer.toString(this.id), this.toString()).hashCode();
	}
}
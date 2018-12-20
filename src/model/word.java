package model;

/**
 * @auther: Liu Zedi.
 * @date: Create in 2018/8/30  12:50
 * @package: model
 * @project: Diction
 */
public class word implements Comparable,Cloneable
{
    private String word;
    private String meaning;
    private String sentence;
    private String sentence_meaning;
    private String ranking;
    private boolean rightOrWrong;

    public word(){
        this.word="";
        this.meaning="";
        this.sentence="";
        this.sentence_meaning="";
        this.ranking="四级";
        this.rightOrWrong=false;
    }
    public word(String word, String meaning) {
        this.word = word;
        this.meaning = meaning;
        this.sentence="";
        this.sentence_meaning="";
        this.ranking="四级";
        this.rightOrWrong=false;
    }

    public word(String word, String meaning, String sentence,String sentence_meaning) {
        this.word = word;
        this.meaning = meaning;
        this.sentence = sentence;
        this.sentence_meaning=sentence_meaning;
        this.ranking="四级";
        this.rightOrWrong=false;
    }

    public word(String word, String meaning, String sentence,String sentence_meaning, String ranking) {
        this.word = word;
        this.meaning = meaning;
        this.sentence = sentence;
        this.sentence_meaning=sentence_meaning;
        this.ranking=ranking;
        this.rightOrWrong=false;
    }


    public void setWord(String word) {
        this.word = word;
    }

    public void setMeaning(String meaning) {
        this.meaning = meaning;
    }

    public void setSentence(String sentence) {
        this.sentence = sentence;
    }

    public void setRightOrWrong(boolean rightOrWrong) {
        this.rightOrWrong = rightOrWrong;
    }

    public void setRanking(String ranking) {
        this.ranking = ranking;
    }

    public void setSentence_meaning(String sentence_meaning) {
        this.sentence_meaning = sentence_meaning;
    }

    public String getWord() {

        return word;
    }

    public String getSentence_meaning() {
        return sentence_meaning;
    }

    public boolean isRightOrWrong() {
        return rightOrWrong;
    }

    public String getMeaning() {
        return meaning;
    }

    public String getSentence() {
        return sentence;
    }


    public String getRanking() {
        return ranking;
    }

    @Override
    public String toString() {
        return "word{" +
                "word='" + word + '\'' +
                ", meaning='" + meaning + '\'' +
                ", sentence='" + sentence + '\'' +
                ", sentence_meaning='" + sentence_meaning + '\'' +
                ", ranking='" + ranking + '\'' +
                ", rightOrWrong=" + rightOrWrong +
                '}';
    }

    /**
     * Compares this object with the specified object for order.  Returns a
     * negative integer, zero, or a positive integer as this object is less
     * than, equal to, or greater than the specified object.
     *
     * <p>The implementor must ensure <tt>sgn(x.compareTo(y)) ==
     * -sgn(y.compareTo(x))</tt> for all <tt>x</tt> and <tt>y</tt>.  (This
     * implies that <tt>x.compareTo(y)</tt> must throw an exception iff
     * <tt>y.compareTo(x)</tt> throws an exception.)
     *
     * <p>The implementor must also ensure that the relation is transitive:
     * <tt>(x.compareTo(y)&gt;0 &amp;&amp; y.compareTo(z)&gt;0)</tt> implies
     * <tt>x.compareTo(z)&gt;0</tt>.
     *
     * <p>Finally, the implementor must ensure that <tt>x.compareTo(y)==0</tt>
     * implies that <tt>sgn(x.compareTo(z)) == sgn(y.compareTo(z))</tt>, for
     * all <tt>z</tt>.
     *
     * <p>It is strongly recommended, but <i>not</i> strictly required that
     * <tt>(x.compareTo(y)==0) == (x.equals(y))</tt>.  Generally speaking, any
     * class that implements the <tt>Comparable</tt> interface and violates
     * this condition should clearly indicate this fact.  The recommended
     * language is "Note: this class has a natural ordering that is
     * inconsistent with equals."
     *
     * <p>In the foregoing description, the notation
     * <tt>sgn(</tt><i>expression</i><tt>)</tt> designates the mathematical
     * <i>signum</i> function, which is defined to return one of <tt>-1</tt>,
     * <tt>0</tt>, or <tt>1</tt> according to whether the value of
     * <i>expression</i> is negative, zero or positive.
     *
     * @param o the object to be compared.
     * @return a negative integer, zero, or a positive integer as this object
     * is less than, equal to, or greater than the specified object.
     * @throws NullPointerException if the specified object is null
     * @throws ClassCastException   if the specified object's type prevents it
     *                              from being compared to this object.
     */
    @Override
    public int compareTo(Object o) {
        word temp=(word)o;
        return this.getWord().compareToIgnoreCase(temp.getWord());
    }

    /**
     * Creates and returns a copy of this object.  The precise meaning
     * of "copy" may depend on the class of the object. The general
     * intent is that, for any object {@code x}, the expression:
     * <blockquote>
     * <pre>
     * x.clone() != x</pre></blockquote>
     * will be true, and that the expression:
     * <blockquote>
     * <pre>
     * x.clone().getClass() == x.getClass()</pre></blockquote>
     * will be {@code true}, but these are not absolute requirements.
     * While it is typically the case that:
     * <blockquote>
     * <pre>
     * x.clone().equals(x)</pre></blockquote>
     * will be {@code true}, this is not an absolute requirement.
     * <p>
     * By convention, the returned object should be obtained by calling
     * {@code super.clone}.  If a class and all of its superclasses (except
     * {@code Object}) obey this convention, it will be the case that
     * {@code x.clone().getClass() == x.getClass()}.
     * <p>
     * By convention, the object returned by this method should be independent
     * of this object (which is being cloned).  To achieve this independence,
     * it may be necessary to modify one or more fields of the object returned
     * by {@code super.clone} before returning it.  Typically, this means
     * copying any mutable objects that comprise the internal "deep structure"
     * of the object being cloned and replacing the references to these
     * objects with references to the copies.  If a class contains only
     * primitive fields or references to immutable objects, then it is usually
     * the case that no fields in the object returned by {@code super.clone}
     * need to be modified.
     * <p>
     * The method {@code clone} for class {@code Object} performs a
     * specific cloning operation. First, if the class of this object does
     * not implement the interface {@code Cloneable}, then a
     * {@code CloneNotSupportedException} is thrown. Note that all arrays
     * are considered to implement the interface {@code Cloneable} and that
     * the return type of the {@code clone} method of an array type {@code T[]}
     * is {@code T[]} where T is any reference or primitive type.
     * Otherwise, this method creates a new instance of the class of this
     * object and initializes all its fields with exactly the contents of
     * the corresponding fields of this object, as if by assignment; the
     * contents of the fields are not themselves cloned. Thus, this method
     * performs a "shallow copy" of this object, not a "deep copy" operation.
     * <p>
     * The class {@code Object} does not itself implement the interface
     * {@code Cloneable}, so calling the {@code clone} method on an object
     * whose class is {@code Object} will result in throwing an
     * exception at run time.
     *
     * @return a clone of this instance.
     * @throws CloneNotSupportedException if the object's class does not
     *                                    support the {@code Cloneable} interface. Subclasses
     *                                    that override the {@code clone} method can also
     *                                    throw this exception to indicate that an instance cannot
     *                                    be cloned.
     * @see Cloneable
     */
    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}

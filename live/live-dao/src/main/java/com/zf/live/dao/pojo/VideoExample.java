package com.zf.live.dao.pojo;

import com.zf.live.dao.vo.Page;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class VideoExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table video
     *
     * @mbggenerated Mon Dec 15 17:32:50 CST 2014
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table video
     *
     * @mbggenerated Mon Dec 15 17:32:50 CST 2014
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table video
     *
     * @mbggenerated Mon Dec 15 17:32:50 CST 2014
     */
    protected List<Criteria> oredCriteria;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table video
     *
     * @mbggenerated Mon Dec 15 17:32:50 CST 2014
     */
    protected Page page;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table video
     *
     * @mbggenerated Mon Dec 15 17:32:50 CST 2014
     */
    public VideoExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table video
     *
     * @mbggenerated Mon Dec 15 17:32:50 CST 2014
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table video
     *
     * @mbggenerated Mon Dec 15 17:32:50 CST 2014
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table video
     *
     * @mbggenerated Mon Dec 15 17:32:50 CST 2014
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table video
     *
     * @mbggenerated Mon Dec 15 17:32:50 CST 2014
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table video
     *
     * @mbggenerated Mon Dec 15 17:32:50 CST 2014
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table video
     *
     * @mbggenerated Mon Dec 15 17:32:50 CST 2014
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table video
     *
     * @mbggenerated Mon Dec 15 17:32:50 CST 2014
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table video
     *
     * @mbggenerated Mon Dec 15 17:32:50 CST 2014
     */
    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table video
     *
     * @mbggenerated Mon Dec 15 17:32:50 CST 2014
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table video
     *
     * @mbggenerated Mon Dec 15 17:32:50 CST 2014
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table video
     *
     * @mbggenerated Mon Dec 15 17:32:50 CST 2014
     */
    public void setPage(Page page) {
        this.page=page;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table video
     *
     * @mbggenerated Mon Dec 15 17:32:50 CST 2014
     */
    public Page getPage() {
        return page;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table video
     *
     * @mbggenerated Mon Dec 15 17:32:50 CST 2014
     */
    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Long value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Long> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Long> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andFromIsNull() {
            addCriterion("from is null");
            return (Criteria) this;
        }

        public Criteria andFromIsNotNull() {
            addCriterion("from is not null");
            return (Criteria) this;
        }

        public Criteria andFromEqualTo(Byte value) {
            addCriterion("from =", value, "from");
            return (Criteria) this;
        }

        public Criteria andFromNotEqualTo(Byte value) {
            addCriterion("from <>", value, "from");
            return (Criteria) this;
        }

        public Criteria andFromGreaterThan(Byte value) {
            addCriterion("from >", value, "from");
            return (Criteria) this;
        }

        public Criteria andFromGreaterThanOrEqualTo(Byte value) {
            addCriterion("from >=", value, "from");
            return (Criteria) this;
        }

        public Criteria andFromLessThan(Byte value) {
            addCriterion("from <", value, "from");
            return (Criteria) this;
        }

        public Criteria andFromLessThanOrEqualTo(Byte value) {
            addCriterion("from <=", value, "from");
            return (Criteria) this;
        }

        public Criteria andFromIn(List<Byte> values) {
            addCriterion("from in", values, "from");
            return (Criteria) this;
        }

        public Criteria andFromNotIn(List<Byte> values) {
            addCriterion("from not in", values, "from");
            return (Criteria) this;
        }

        public Criteria andFromBetween(Byte value1, Byte value2) {
            addCriterion("from between", value1, value2, "from");
            return (Criteria) this;
        }

        public Criteria andFromNotBetween(Byte value1, Byte value2) {
            addCriterion("from not between", value1, value2, "from");
            return (Criteria) this;
        }

        public Criteria andFromidIsNull() {
            addCriterion("fromid is null");
            return (Criteria) this;
        }

        public Criteria andFromidIsNotNull() {
            addCriterion("fromid is not null");
            return (Criteria) this;
        }

        public Criteria andFromidEqualTo(String value) {
            addCriterion("fromid =", value, "fromid");
            return (Criteria) this;
        }

        public Criteria andFromidNotEqualTo(String value) {
            addCriterion("fromid <>", value, "fromid");
            return (Criteria) this;
        }

        public Criteria andFromidGreaterThan(String value) {
            addCriterion("fromid >", value, "fromid");
            return (Criteria) this;
        }

        public Criteria andFromidGreaterThanOrEqualTo(String value) {
            addCriterion("fromid >=", value, "fromid");
            return (Criteria) this;
        }

        public Criteria andFromidLessThan(String value) {
            addCriterion("fromid <", value, "fromid");
            return (Criteria) this;
        }

        public Criteria andFromidLessThanOrEqualTo(String value) {
            addCriterion("fromid <=", value, "fromid");
            return (Criteria) this;
        }

        public Criteria andFromidLike(String value) {
            addCriterion("fromid like", value, "fromid");
            return (Criteria) this;
        }

        public Criteria andFromidNotLike(String value) {
            addCriterion("fromid not like", value, "fromid");
            return (Criteria) this;
        }

        public Criteria andFromidIn(List<String> values) {
            addCriterion("fromid in", values, "fromid");
            return (Criteria) this;
        }

        public Criteria andFromidNotIn(List<String> values) {
            addCriterion("fromid not in", values, "fromid");
            return (Criteria) this;
        }

        public Criteria andFromidBetween(String value1, String value2) {
            addCriterion("fromid between", value1, value2, "fromid");
            return (Criteria) this;
        }

        public Criteria andFromidNotBetween(String value1, String value2) {
            addCriterion("fromid not between", value1, value2, "fromid");
            return (Criteria) this;
        }

        public Criteria andVideonameIsNull() {
            addCriterion("videoname is null");
            return (Criteria) this;
        }

        public Criteria andVideonameIsNotNull() {
            addCriterion("videoname is not null");
            return (Criteria) this;
        }

        public Criteria andVideonameEqualTo(String value) {
            addCriterion("videoname =", value, "videoname");
            return (Criteria) this;
        }

        public Criteria andVideonameNotEqualTo(String value) {
            addCriterion("videoname <>", value, "videoname");
            return (Criteria) this;
        }

        public Criteria andVideonameGreaterThan(String value) {
            addCriterion("videoname >", value, "videoname");
            return (Criteria) this;
        }

        public Criteria andVideonameGreaterThanOrEqualTo(String value) {
            addCriterion("videoname >=", value, "videoname");
            return (Criteria) this;
        }

        public Criteria andVideonameLessThan(String value) {
            addCriterion("videoname <", value, "videoname");
            return (Criteria) this;
        }

        public Criteria andVideonameLessThanOrEqualTo(String value) {
            addCriterion("videoname <=", value, "videoname");
            return (Criteria) this;
        }

        public Criteria andVideonameLike(String value) {
            addCriterion("videoname like", value, "videoname");
            return (Criteria) this;
        }

        public Criteria andVideonameNotLike(String value) {
            addCriterion("videoname not like", value, "videoname");
            return (Criteria) this;
        }

        public Criteria andVideonameIn(List<String> values) {
            addCriterion("videoname in", values, "videoname");
            return (Criteria) this;
        }

        public Criteria andVideonameNotIn(List<String> values) {
            addCriterion("videoname not in", values, "videoname");
            return (Criteria) this;
        }

        public Criteria andVideonameBetween(String value1, String value2) {
            addCriterion("videoname between", value1, value2, "videoname");
            return (Criteria) this;
        }

        public Criteria andVideonameNotBetween(String value1, String value2) {
            addCriterion("videoname not between", value1, value2, "videoname");
            return (Criteria) this;
        }

        public Criteria andVideoimgIsNull() {
            addCriterion("videoimg is null");
            return (Criteria) this;
        }

        public Criteria andVideoimgIsNotNull() {
            addCriterion("videoimg is not null");
            return (Criteria) this;
        }

        public Criteria andVideoimgEqualTo(String value) {
            addCriterion("videoimg =", value, "videoimg");
            return (Criteria) this;
        }

        public Criteria andVideoimgNotEqualTo(String value) {
            addCriterion("videoimg <>", value, "videoimg");
            return (Criteria) this;
        }

        public Criteria andVideoimgGreaterThan(String value) {
            addCriterion("videoimg >", value, "videoimg");
            return (Criteria) this;
        }

        public Criteria andVideoimgGreaterThanOrEqualTo(String value) {
            addCriterion("videoimg >=", value, "videoimg");
            return (Criteria) this;
        }

        public Criteria andVideoimgLessThan(String value) {
            addCriterion("videoimg <", value, "videoimg");
            return (Criteria) this;
        }

        public Criteria andVideoimgLessThanOrEqualTo(String value) {
            addCriterion("videoimg <=", value, "videoimg");
            return (Criteria) this;
        }

        public Criteria andVideoimgLike(String value) {
            addCriterion("videoimg like", value, "videoimg");
            return (Criteria) this;
        }

        public Criteria andVideoimgNotLike(String value) {
            addCriterion("videoimg not like", value, "videoimg");
            return (Criteria) this;
        }

        public Criteria andVideoimgIn(List<String> values) {
            addCriterion("videoimg in", values, "videoimg");
            return (Criteria) this;
        }

        public Criteria andVideoimgNotIn(List<String> values) {
            addCriterion("videoimg not in", values, "videoimg");
            return (Criteria) this;
        }

        public Criteria andVideoimgBetween(String value1, String value2) {
            addCriterion("videoimg between", value1, value2, "videoimg");
            return (Criteria) this;
        }

        public Criteria andVideoimgNotBetween(String value1, String value2) {
            addCriterion("videoimg not between", value1, value2, "videoimg");
            return (Criteria) this;
        }

        public Criteria andVideolengthIsNull() {
            addCriterion("videolength is null");
            return (Criteria) this;
        }

        public Criteria andVideolengthIsNotNull() {
            addCriterion("videolength is not null");
            return (Criteria) this;
        }

        public Criteria andVideolengthEqualTo(Long value) {
            addCriterion("videolength =", value, "videolength");
            return (Criteria) this;
        }

        public Criteria andVideolengthNotEqualTo(Long value) {
            addCriterion("videolength <>", value, "videolength");
            return (Criteria) this;
        }

        public Criteria andVideolengthGreaterThan(Long value) {
            addCriterion("videolength >", value, "videolength");
            return (Criteria) this;
        }

        public Criteria andVideolengthGreaterThanOrEqualTo(Long value) {
            addCriterion("videolength >=", value, "videolength");
            return (Criteria) this;
        }

        public Criteria andVideolengthLessThan(Long value) {
            addCriterion("videolength <", value, "videolength");
            return (Criteria) this;
        }

        public Criteria andVideolengthLessThanOrEqualTo(Long value) {
            addCriterion("videolength <=", value, "videolength");
            return (Criteria) this;
        }

        public Criteria andVideolengthIn(List<Long> values) {
            addCriterion("videolength in", values, "videolength");
            return (Criteria) this;
        }

        public Criteria andVideolengthNotIn(List<Long> values) {
            addCriterion("videolength not in", values, "videolength");
            return (Criteria) this;
        }

        public Criteria andVideolengthBetween(Long value1, Long value2) {
            addCriterion("videolength between", value1, value2, "videolength");
            return (Criteria) this;
        }

        public Criteria andVideolengthNotBetween(Long value1, Long value2) {
            addCriterion("videolength not between", value1, value2, "videolength");
            return (Criteria) this;
        }

        public Criteria andPraiseIsNull() {
            addCriterion("praise is null");
            return (Criteria) this;
        }

        public Criteria andPraiseIsNotNull() {
            addCriterion("praise is not null");
            return (Criteria) this;
        }

        public Criteria andPraiseEqualTo(Long value) {
            addCriterion("praise =", value, "praise");
            return (Criteria) this;
        }

        public Criteria andPraiseNotEqualTo(Long value) {
            addCriterion("praise <>", value, "praise");
            return (Criteria) this;
        }

        public Criteria andPraiseGreaterThan(Long value) {
            addCriterion("praise >", value, "praise");
            return (Criteria) this;
        }

        public Criteria andPraiseGreaterThanOrEqualTo(Long value) {
            addCriterion("praise >=", value, "praise");
            return (Criteria) this;
        }

        public Criteria andPraiseLessThan(Long value) {
            addCriterion("praise <", value, "praise");
            return (Criteria) this;
        }

        public Criteria andPraiseLessThanOrEqualTo(Long value) {
            addCriterion("praise <=", value, "praise");
            return (Criteria) this;
        }

        public Criteria andPraiseIn(List<Long> values) {
            addCriterion("praise in", values, "praise");
            return (Criteria) this;
        }

        public Criteria andPraiseNotIn(List<Long> values) {
            addCriterion("praise not in", values, "praise");
            return (Criteria) this;
        }

        public Criteria andPraiseBetween(Long value1, Long value2) {
            addCriterion("praise between", value1, value2, "praise");
            return (Criteria) this;
        }

        public Criteria andPraiseNotBetween(Long value1, Long value2) {
            addCriterion("praise not between", value1, value2, "praise");
            return (Criteria) this;
        }

        public Criteria andFlagIsNull() {
            addCriterion("flag is null");
            return (Criteria) this;
        }

        public Criteria andFlagIsNotNull() {
            addCriterion("flag is not null");
            return (Criteria) this;
        }

        public Criteria andFlagEqualTo(Integer value) {
            addCriterion("flag =", value, "flag");
            return (Criteria) this;
        }

        public Criteria andFlagNotEqualTo(Integer value) {
            addCriterion("flag <>", value, "flag");
            return (Criteria) this;
        }

        public Criteria andFlagGreaterThan(Integer value) {
            addCriterion("flag >", value, "flag");
            return (Criteria) this;
        }

        public Criteria andFlagGreaterThanOrEqualTo(Integer value) {
            addCriterion("flag >=", value, "flag");
            return (Criteria) this;
        }

        public Criteria andFlagLessThan(Integer value) {
            addCriterion("flag <", value, "flag");
            return (Criteria) this;
        }

        public Criteria andFlagLessThanOrEqualTo(Integer value) {
            addCriterion("flag <=", value, "flag");
            return (Criteria) this;
        }

        public Criteria andFlagIn(List<Integer> values) {
            addCriterion("flag in", values, "flag");
            return (Criteria) this;
        }

        public Criteria andFlagNotIn(List<Integer> values) {
            addCriterion("flag not in", values, "flag");
            return (Criteria) this;
        }

        public Criteria andFlagBetween(Integer value1, Integer value2) {
            addCriterion("flag between", value1, value2, "flag");
            return (Criteria) this;
        }

        public Criteria andFlagNotBetween(Integer value1, Integer value2) {
            addCriterion("flag not between", value1, value2, "flag");
            return (Criteria) this;
        }

        public Criteria andCreatetimeIsNull() {
            addCriterion("createtime is null");
            return (Criteria) this;
        }

        public Criteria andCreatetimeIsNotNull() {
            addCriterion("createtime is not null");
            return (Criteria) this;
        }

        public Criteria andCreatetimeEqualTo(Date value) {
            addCriterion("createtime =", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeNotEqualTo(Date value) {
            addCriterion("createtime <>", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeGreaterThan(Date value) {
            addCriterion("createtime >", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeGreaterThanOrEqualTo(Date value) {
            addCriterion("createtime >=", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeLessThan(Date value) {
            addCriterion("createtime <", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeLessThanOrEqualTo(Date value) {
            addCriterion("createtime <=", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeIn(List<Date> values) {
            addCriterion("createtime in", values, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeNotIn(List<Date> values) {
            addCriterion("createtime not in", values, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeBetween(Date value1, Date value2) {
            addCriterion("createtime between", value1, value2, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeNotBetween(Date value1, Date value2) {
            addCriterion("createtime not between", value1, value2, "createtime");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table video
     *
     * @mbggenerated do_not_delete_during_merge Mon Dec 15 17:32:50 CST 2014
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table video
     *
     * @mbggenerated Mon Dec 15 17:32:50 CST 2014
     */
    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}
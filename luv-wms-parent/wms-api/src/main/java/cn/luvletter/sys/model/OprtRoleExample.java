package cn.luvletter.sys.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class OprtRoleExample implements Serializable {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    private static final long serialVersionUID = 1L;

    public OprtRoleExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria implements Serializable {
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

        protected void addCriterionForJDBCDate(String condition, Date value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value.getTime()), property);
        }

        protected void addCriterionForJDBCDate(String condition, List<Date> values, String property) {
            if (values == null || values.size() == 0) {
                throw new RuntimeException("Value list for " + property + " cannot be null or empty");
            }
            List<java.sql.Date> dateList = new ArrayList<java.sql.Date>();
            Iterator<Date> iter = values.iterator();
            while (iter.hasNext()) {
                dateList.add(new java.sql.Date(iter.next().getTime()));
            }
            addCriterion(condition, dateList, property);
        }

        protected void addCriterionForJDBCDate(String condition, Date value1, Date value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value1.getTime()), new java.sql.Date(value2.getTime()), property);
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andOprtNoIsNull() {
            addCriterion("oprt_no is null");
            return (Criteria) this;
        }

        public Criteria andOprtNoIsNotNull() {
            addCriterion("oprt_no is not null");
            return (Criteria) this;
        }

        public Criteria andOprtNoEqualTo(String value) {
            addCriterion("oprt_no =", value, "oprtNo");
            return (Criteria) this;
        }

        public Criteria andOprtNoNotEqualTo(String value) {
            addCriterion("oprt_no <>", value, "oprtNo");
            return (Criteria) this;
        }

        public Criteria andOprtNoGreaterThan(String value) {
            addCriterion("oprt_no >", value, "oprtNo");
            return (Criteria) this;
        }

        public Criteria andOprtNoGreaterThanOrEqualTo(String value) {
            addCriterion("oprt_no >=", value, "oprtNo");
            return (Criteria) this;
        }

        public Criteria andOprtNoLessThan(String value) {
            addCriterion("oprt_no <", value, "oprtNo");
            return (Criteria) this;
        }

        public Criteria andOprtNoLessThanOrEqualTo(String value) {
            addCriterion("oprt_no <=", value, "oprtNo");
            return (Criteria) this;
        }

        public Criteria andOprtNoLike(String value) {
            addCriterion("oprt_no like", value, "oprtNo");
            return (Criteria) this;
        }

        public Criteria andOprtNoNotLike(String value) {
            addCriterion("oprt_no not like", value, "oprtNo");
            return (Criteria) this;
        }

        public Criteria andOprtNoIn(List<String> values) {
            addCriterion("oprt_no in", values, "oprtNo");
            return (Criteria) this;
        }

        public Criteria andOprtNoNotIn(List<String> values) {
            addCriterion("oprt_no not in", values, "oprtNo");
            return (Criteria) this;
        }

        public Criteria andOprtNoBetween(String value1, String value2) {
            addCriterion("oprt_no between", value1, value2, "oprtNo");
            return (Criteria) this;
        }

        public Criteria andOprtNoNotBetween(String value1, String value2) {
            addCriterion("oprt_no not between", value1, value2, "oprtNo");
            return (Criteria) this;
        }

        public Criteria andRoleNoIsNull() {
            addCriterion("role_no is null");
            return (Criteria) this;
        }

        public Criteria andRoleNoIsNotNull() {
            addCriterion("role_no is not null");
            return (Criteria) this;
        }

        public Criteria andRoleNoEqualTo(String value) {
            addCriterion("role_no =", value, "roleNo");
            return (Criteria) this;
        }

        public Criteria andRoleNoNotEqualTo(String value) {
            addCriterion("role_no <>", value, "roleNo");
            return (Criteria) this;
        }

        public Criteria andRoleNoGreaterThan(String value) {
            addCriterion("role_no >", value, "roleNo");
            return (Criteria) this;
        }

        public Criteria andRoleNoGreaterThanOrEqualTo(String value) {
            addCriterion("role_no >=", value, "roleNo");
            return (Criteria) this;
        }

        public Criteria andRoleNoLessThan(String value) {
            addCriterion("role_no <", value, "roleNo");
            return (Criteria) this;
        }

        public Criteria andRoleNoLessThanOrEqualTo(String value) {
            addCriterion("role_no <=", value, "roleNo");
            return (Criteria) this;
        }

        public Criteria andRoleNoLike(String value) {
            addCriterion("role_no like", value, "roleNo");
            return (Criteria) this;
        }

        public Criteria andRoleNoNotLike(String value) {
            addCriterion("role_no not like", value, "roleNo");
            return (Criteria) this;
        }

        public Criteria andRoleNoIn(List<String> values) {
            addCriterion("role_no in", values, "roleNo");
            return (Criteria) this;
        }

        public Criteria andRoleNoNotIn(List<String> values) {
            addCriterion("role_no not in", values, "roleNo");
            return (Criteria) this;
        }

        public Criteria andRoleNoBetween(String value1, String value2) {
            addCriterion("role_no between", value1, value2, "roleNo");
            return (Criteria) this;
        }

        public Criteria andRoleNoNotBetween(String value1, String value2) {
            addCriterion("role_no not between", value1, value2, "roleNo");
            return (Criteria) this;
        }

        public Criteria andGmtCreateIsNull() {
            addCriterion("gmt_create is null");
            return (Criteria) this;
        }

        public Criteria andGmtCreateIsNotNull() {
            addCriterion("gmt_create is not null");
            return (Criteria) this;
        }

        public Criteria andGmtCreateEqualTo(Date value) {
            addCriterionForJDBCDate("gmt_create =", value, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateNotEqualTo(Date value) {
            addCriterionForJDBCDate("gmt_create <>", value, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateGreaterThan(Date value) {
            addCriterionForJDBCDate("gmt_create >", value, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("gmt_create >=", value, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateLessThan(Date value) {
            addCriterionForJDBCDate("gmt_create <", value, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("gmt_create <=", value, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateIn(List<Date> values) {
            addCriterionForJDBCDate("gmt_create in", values, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateNotIn(List<Date> values) {
            addCriterionForJDBCDate("gmt_create not in", values, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("gmt_create between", value1, value2, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("gmt_create not between", value1, value2, "gmtCreate");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria implements Serializable {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion implements Serializable {
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
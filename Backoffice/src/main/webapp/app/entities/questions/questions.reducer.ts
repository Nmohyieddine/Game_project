import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IQuestions, defaultValue } from 'app/shared/model/questions.model';

export const ACTION_TYPES = {
  FETCH_QUESTIONS_LIST: 'questions/FETCH_QUESTIONS_LIST',
  FETCH_QUESTIONS: 'questions/FETCH_QUESTIONS',
  CREATE_QUESTIONS: 'questions/CREATE_QUESTIONS',
  UPDATE_QUESTIONS: 'questions/UPDATE_QUESTIONS',
  DELETE_QUESTIONS: 'questions/DELETE_QUESTIONS',
  RESET: 'questions/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IQuestions>,
  entity: defaultValue,
  updating: false,
  totalItems: 0,
  updateSuccess: false
};

export type QuestionsState = Readonly<typeof initialState>;

// Reducer

export default (state: QuestionsState = initialState, action): QuestionsState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_QUESTIONS_LIST):
    case REQUEST(ACTION_TYPES.FETCH_QUESTIONS):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_QUESTIONS):
    case REQUEST(ACTION_TYPES.UPDATE_QUESTIONS):
    case REQUEST(ACTION_TYPES.DELETE_QUESTIONS):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_QUESTIONS_LIST):
    case FAILURE(ACTION_TYPES.FETCH_QUESTIONS):
    case FAILURE(ACTION_TYPES.CREATE_QUESTIONS):
    case FAILURE(ACTION_TYPES.UPDATE_QUESTIONS):
    case FAILURE(ACTION_TYPES.DELETE_QUESTIONS):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_QUESTIONS_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data,
        totalItems: parseInt(action.payload.headers['x-total-count'], 10)
      };
    case SUCCESS(ACTION_TYPES.FETCH_QUESTIONS):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_QUESTIONS):
    case SUCCESS(ACTION_TYPES.UPDATE_QUESTIONS):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_QUESTIONS):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: {}
      };
    case ACTION_TYPES.RESET:
      return {
        ...initialState
      };
    default:
      return state;
  }
};

const apiUrl = 'api/questions';

// Actions

export const getEntities: ICrudGetAllAction<IQuestions> = (page, size, sort) => {
  const requestUrl = `${apiUrl}${sort ? `?page=${page}&size=${size}&sort=${sort}` : ''}`;
  return {
    type: ACTION_TYPES.FETCH_QUESTIONS_LIST,
    payload: axios.get<IQuestions>(`${requestUrl}${sort ? '&' : '?'}cacheBuster=${new Date().getTime()}`)
  };
};

export const getEntity: ICrudGetAction<IQuestions> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_QUESTIONS,
    payload: axios.get<IQuestions>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IQuestions> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_QUESTIONS,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IQuestions> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_QUESTIONS,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IQuestions> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_QUESTIONS,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});

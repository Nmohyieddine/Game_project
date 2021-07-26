import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IReponses, defaultValue } from 'app/shared/model/reponses.model';

export const ACTION_TYPES = {
  FETCH_REPONSES_LIST: 'reponses/FETCH_REPONSES_LIST',
  FETCH_REPONSES: 'reponses/FETCH_REPONSES',
  CREATE_REPONSES: 'reponses/CREATE_REPONSES',
  UPDATE_REPONSES: 'reponses/UPDATE_REPONSES',
  DELETE_REPONSES: 'reponses/DELETE_REPONSES',
  RESET: 'reponses/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IReponses>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false
};

export type ReponsesState = Readonly<typeof initialState>;

// Reducer

export default (state: ReponsesState = initialState, action): ReponsesState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_REPONSES_LIST):
    case REQUEST(ACTION_TYPES.FETCH_REPONSES):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_REPONSES):
    case REQUEST(ACTION_TYPES.UPDATE_REPONSES):
    case REQUEST(ACTION_TYPES.DELETE_REPONSES):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_REPONSES_LIST):
    case FAILURE(ACTION_TYPES.FETCH_REPONSES):
    case FAILURE(ACTION_TYPES.CREATE_REPONSES):
    case FAILURE(ACTION_TYPES.UPDATE_REPONSES):
    case FAILURE(ACTION_TYPES.DELETE_REPONSES):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_REPONSES_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_REPONSES):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_REPONSES):
    case SUCCESS(ACTION_TYPES.UPDATE_REPONSES):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_REPONSES):
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

const apiUrl = 'api/reponses';

// Actions

export const getEntities: ICrudGetAllAction<IReponses> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_REPONSES_LIST,
  payload: axios.get<IReponses>(`${apiUrl}?cacheBuster=${new Date().getTime()}`)
});

export const getEntity: ICrudGetAction<IReponses> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_REPONSES,
    payload: axios.get<IReponses>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IReponses> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_REPONSES,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IReponses> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_REPONSES,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IReponses> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_REPONSES,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});

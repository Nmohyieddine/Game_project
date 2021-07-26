import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IPropositions, defaultValue } from 'app/shared/model/propositions.model';

export const ACTION_TYPES = {
  FETCH_PROPOSITIONS_LIST: 'propositions/FETCH_PROPOSITIONS_LIST',
  FETCH_PROPOSITIONS: 'propositions/FETCH_PROPOSITIONS',
  CREATE_PROPOSITIONS: 'propositions/CREATE_PROPOSITIONS',
  UPDATE_PROPOSITIONS: 'propositions/UPDATE_PROPOSITIONS',
  DELETE_PROPOSITIONS: 'propositions/DELETE_PROPOSITIONS',
  RESET: 'propositions/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IPropositions>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false
};

export type PropositionsState = Readonly<typeof initialState>;

// Reducer

export default (state: PropositionsState = initialState, action): PropositionsState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_PROPOSITIONS_LIST):
    case REQUEST(ACTION_TYPES.FETCH_PROPOSITIONS):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_PROPOSITIONS):
    case REQUEST(ACTION_TYPES.UPDATE_PROPOSITIONS):
    case REQUEST(ACTION_TYPES.DELETE_PROPOSITIONS):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_PROPOSITIONS_LIST):
    case FAILURE(ACTION_TYPES.FETCH_PROPOSITIONS):
    case FAILURE(ACTION_TYPES.CREATE_PROPOSITIONS):
    case FAILURE(ACTION_TYPES.UPDATE_PROPOSITIONS):
    case FAILURE(ACTION_TYPES.DELETE_PROPOSITIONS):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_PROPOSITIONS_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_PROPOSITIONS):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_PROPOSITIONS):
    case SUCCESS(ACTION_TYPES.UPDATE_PROPOSITIONS):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_PROPOSITIONS):
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

const apiUrl = 'api/propositions';

// Actions

export const getEntities: ICrudGetAllAction<IPropositions> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_PROPOSITIONS_LIST,
  payload: axios.get<IPropositions>(`${apiUrl}?cacheBuster=${new Date().getTime()}`)
});

export const getEntity: ICrudGetAction<IPropositions> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_PROPOSITIONS,
    payload: axios.get<IPropositions>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IPropositions> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_PROPOSITIONS,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IPropositions> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_PROPOSITIONS,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IPropositions> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_PROPOSITIONS,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});

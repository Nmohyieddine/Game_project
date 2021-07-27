import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { IQuestions } from 'app/shared/model/questions.model';
import { getEntities as getQuestions } from 'app/entities/questions/questions.reducer';
import { getEntity, updateEntity, createEntity, reset } from './reponses.reducer';
import { IReponses } from 'app/shared/model/reponses.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer, convertDateTimeToServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IReponsesUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export interface IReponsesUpdateState {
  isNew: boolean;
  questionsId: string;
}

export class ReponsesUpdate extends React.Component<IReponsesUpdateProps, IReponsesUpdateState> {
  constructor(props) {
    super(props);
    this.state = {
      questionsId: '0',
      isNew: !this.props.match.params || !this.props.match.params.id
    };
  }

  componentWillUpdate(nextProps, nextState) {
    if (nextProps.updateSuccess !== this.props.updateSuccess && nextProps.updateSuccess) {
      this.handleClose();
    }
  }

  componentDidMount() {
    if (this.state.isNew) {
      this.props.reset();
    } else {
      this.props.getEntity(this.props.match.params.id);
    }

    this.props.getQuestions();
  }

  saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const { reponsesEntity } = this.props;
      const entity = {
        ...reponsesEntity,
        ...values
      };

      if (this.state.isNew) {
        this.props.createEntity(entity);
      } else {
        this.props.updateEntity(entity);
      }
    }
  };

  handleClose = () => {
    this.props.history.push('/entity/reponses');
  };

  render() {
    const { reponsesEntity, questions, loading, updating } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="backofficeApp.reponses.home.createOrEditLabel">
              <Translate contentKey="backofficeApp.reponses.home.createOrEditLabel">Create or edit a Reponses</Translate>
            </h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : reponsesEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="reponses-id">
                      <Translate contentKey="global.field.id">ID</Translate>
                    </Label>
                    <AvInput id="reponses-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="idreponseLabel" for="reponses-idreponse">
                    <Translate contentKey="backofficeApp.reponses.idreponse">Idreponse</Translate>
                  </Label>
                  <AvField
                    id="reponses-idreponse"
                    type="string"
                    className="form-control"
                    name="idreponse"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') },
                      number: { value: true, errorMessage: translate('entity.validation.number') }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="reponseLabel" for="reponses-reponse">
                    <Translate contentKey="backofficeApp.reponses.reponse">Reponse</Translate>
                  </Label>
                  <AvField id="reponses-reponse" type="text" name="reponse" />
                </AvGroup>
                <AvGroup>
                  <Label for="reponses-questions">
                    <Translate contentKey="backofficeApp.reponses.questions">Questions</Translate>
                  </Label>
                  <AvInput id="reponses-questions" type="select" className="form-control" name="questionsId">
                    <option value="" key="0" />
                    {questions
                      ? questions.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.idquestion}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <Button tag={Link} id="cancel-save" to="/entity/reponses" replace color="info">
                  <FontAwesomeIcon icon="arrow-left" />
                  &nbsp;
                  <span className="d-none d-md-inline">
                    <Translate contentKey="entity.action.back">Back</Translate>
                  </span>
                </Button>
                &nbsp;
                <Button color="primary" id="save-entity" type="submit" disabled={updating}>
                  <FontAwesomeIcon icon="save" />
                  &nbsp;
                  <Translate contentKey="entity.action.save">Save</Translate>
                </Button>
              </AvForm>
            )}
          </Col>
        </Row>
      </div>
    );
  }
}

const mapStateToProps = (storeState: IRootState) => ({
  questions: storeState.questions.entities,
  reponsesEntity: storeState.reponses.entity,
  loading: storeState.reponses.loading,
  updating: storeState.reponses.updating,
  updateSuccess: storeState.reponses.updateSuccess
});

const mapDispatchToProps = {
  getQuestions,
  getEntity,
  updateEntity,
  createEntity,
  reset
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(ReponsesUpdate);

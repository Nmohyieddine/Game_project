import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { IPropositions } from 'app/shared/model/propositions.model';
import { getEntities as getPropositions } from 'app/entities/propositions/propositions.reducer';
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
  propositionId: string;
  questionId: string;
}

export class ReponsesUpdate extends React.Component<IReponsesUpdateProps, IReponsesUpdateState> {
  constructor(props) {
    super(props);
    this.state = {
      propositionId: '0',
      questionId: '0',
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

    this.props.getPropositions();
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
    const { reponsesEntity, propositions, questions, loading, updating } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="backofficeApp.reponses.home.createOrEditLabel">Create or edit a Reponses</h2>
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
                    <Label for="reponses-id">ID</Label>
                    <AvInput id="reponses-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="idreponseLabel" for="reponses-idreponse">
                    Idreponse
                  </Label>
                  <AvField
                    id="reponses-idreponse"
                    type="string"
                    className="form-control"
                    name="idreponse"
                    validate={{
                      required: { value: true, errorMessage: 'This field is required.' },
                      number: { value: true, errorMessage: 'This field should be a number.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label for="reponses-proposition">Proposition</Label>
                  <AvInput id="reponses-proposition" type="select" className="form-control" name="propositionId">
                    <option value="" key="0" />
                    {propositions
                      ? propositions.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.id}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label for="reponses-question">Question</Label>
                  <AvInput id="reponses-question" type="select" className="form-control" name="questionId">
                    <option value="" key="0" />
                    {questions
                      ? questions.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.id}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <Button tag={Link} id="cancel-save" to="/entity/reponses" replace color="info">
                  <FontAwesomeIcon icon="arrow-left" />
                  &nbsp;
                  <span className="d-none d-md-inline">Back</span>
                </Button>
                &nbsp;
                <Button color="primary" id="save-entity" type="submit" disabled={updating}>
                  <FontAwesomeIcon icon="save" />
                  &nbsp; Save
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
  propositions: storeState.propositions.entities,
  questions: storeState.questions.entities,
  reponsesEntity: storeState.reponses.entity,
  loading: storeState.reponses.loading,
  updating: storeState.reponses.updating,
  updateSuccess: storeState.reponses.updateSuccess
});

const mapDispatchToProps = {
  getPropositions,
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

import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './questions.reducer';
import { IQuestions } from 'app/shared/model/questions.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IQuestionsDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class QuestionsDetail extends React.Component<IQuestionsDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { questionsEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            Questions [<b>{questionsEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="idquestion">Idquestion</span>
            </dt>
            <dd>{questionsEntity.idquestion}</dd>
            <dt>
              <span id="question">Question</span>
            </dt>
            <dd>{questionsEntity.question}</dd>
          </dl>
          <Button tag={Link} to="/entity/questions" replace color="info">
            <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
          </Button>
          &nbsp;
          <Button tag={Link} to={`/entity/questions/${questionsEntity.id}/edit`} replace color="primary">
            <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
          </Button>
        </Col>
      </Row>
    );
  }
}

const mapStateToProps = ({ questions }: IRootState) => ({
  questionsEntity: questions.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(QuestionsDetail);

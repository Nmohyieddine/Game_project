import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './reponses.reducer';
import { IReponses } from 'app/shared/model/reponses.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IReponsesDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class ReponsesDetail extends React.Component<IReponsesDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { reponsesEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            <Translate contentKey="backofficeApp.reponses.detail.title">Reponses</Translate> [<b>{reponsesEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="idreponse">
                <Translate contentKey="backofficeApp.reponses.idreponse">Idreponse</Translate>
              </span>
            </dt>
            <dd>{reponsesEntity.idreponse}</dd>
            <dt>
              <span id="reponse">
                <Translate contentKey="backofficeApp.reponses.reponse">Reponse</Translate>
              </span>
            </dt>
            <dd>{reponsesEntity.reponse}</dd>
            <dt>
              <Translate contentKey="backofficeApp.reponses.questions">Questions</Translate>
            </dt>
            <dd>{reponsesEntity.questionsIdquestion ? reponsesEntity.questionsIdquestion : ''}</dd>
          </dl>
          <Button tag={Link} to="/entity/reponses" replace color="info">
            <FontAwesomeIcon icon="arrow-left" />{' '}
            <span className="d-none d-md-inline">
              <Translate contentKey="entity.action.back">Back</Translate>
            </span>
          </Button>
          &nbsp;
          <Button tag={Link} to={`/entity/reponses/${reponsesEntity.id}/edit`} replace color="primary">
            <FontAwesomeIcon icon="pencil-alt" />{' '}
            <span className="d-none d-md-inline">
              <Translate contentKey="entity.action.edit">Edit</Translate>
            </span>
          </Button>
        </Col>
      </Row>
    );
  }
}

const mapStateToProps = ({ reponses }: IRootState) => ({
  reponsesEntity: reponses.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(ReponsesDetail);

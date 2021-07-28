import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './propositions.reducer';
import { IPropositions } from 'app/shared/model/propositions.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IPropositionsDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class PropositionsDetail extends React.Component<IPropositionsDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { propositionsEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            Propositions [<b>{propositionsEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="idpropositions">Idpropositions</span>
            </dt>
            <dd>{propositionsEntity.idpropositions}</dd>
            <dt>
              <span id="proposition">Proposition</span>
            </dt>
            <dd>{propositionsEntity.proposition}</dd>
            <dt>Questions</dt>
            <dd>{propositionsEntity.questionsIdquestion ? propositionsEntity.questionsIdquestion : ''}</dd>
          </dl>
          <Button tag={Link} to="/entity/propositions" replace color="info">
            <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
          </Button>
          &nbsp;
          <Button tag={Link} to={`/entity/propositions/${propositionsEntity.id}/edit`} replace color="primary">
            <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
          </Button>
        </Col>
      </Row>
    );
  }
}

const mapStateToProps = ({ propositions }: IRootState) => ({
  propositionsEntity: propositions.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(PropositionsDetail);

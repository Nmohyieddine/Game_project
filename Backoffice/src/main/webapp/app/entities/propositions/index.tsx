import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import Propositions from './propositions';
import PropositionsDetail from './propositions-detail';
import PropositionsUpdate from './propositions-update';
import PropositionsDeleteDialog from './propositions-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={PropositionsUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={PropositionsUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={PropositionsDetail} />
      <ErrorBoundaryRoute path={match.url} component={Propositions} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={PropositionsDeleteDialog} />
  </>
);

export default Routes;

import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import Reponses from './reponses';
import ReponsesDetail from './reponses-detail';
import ReponsesUpdate from './reponses-update';
import ReponsesDeleteDialog from './reponses-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={ReponsesUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={ReponsesUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={ReponsesDetail} />
      <ErrorBoundaryRoute path={match.url} component={Reponses} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={ReponsesDeleteDialog} />
  </>
);

export default Routes;

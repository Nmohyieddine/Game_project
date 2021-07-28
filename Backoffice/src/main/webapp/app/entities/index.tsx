import React from 'react';
import { Switch } from 'react-router-dom';

// tslint:disable-next-line:no-unused-variable
import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import Questions from './questions';
import Reponses from './reponses';
import Propositions from './propositions';
/* jhipster-needle-add-route-import - JHipster will add routes here */

const Routes = ({ match }) => (
  <div>
    <Switch>
      {/* prettier-ignore */}
      <ErrorBoundaryRoute path={`${match.url}/questions`} component={Questions} />
      <ErrorBoundaryRoute path={`${match.url}/reponses`} component={Reponses} />
      <ErrorBoundaryRoute path={`${match.url}/propositions`} component={Propositions} />
      {/* jhipster-needle-add-route-path - JHipster will add routes here */}
    </Switch>
  </div>
);

export default Routes;

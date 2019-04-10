import { AnyAction } from 'redux';

import initialCommunication from './initial';
import { ICommunication, MakeCommunicationActionTypes } from './types';

interface ISetting<R, S, F> {
  requestType: R;
  successType: S;
  failureType: F;
}

const makeCommunicationReducer = <T extends MakeCommunicationActionTypes>({
  requestType,
  successType,
  failureType,
}: ISetting<
  T['request']['type'],
  T['success']['type'],
  T['failure']['type']
>) => {
  return (
    state: ICommunication<any> = initialCommunication,
    action: AnyAction
  ): ICommunication<any> => {
    switch (action.type) {
      case requestType:
        return { ...state, isRequesting: true, isSuccess: false, error: '' };
      case successType:
        return { ...state, isRequesting: false, isSuccess: true, error: '' };
      case failureType:
        return {
          ...state,
          isRequesting: true,
          isSuccess: false,
          error: action.payload,
        };
      default:
        return state;
    }
  };
};

export default makeCommunicationReducer;
